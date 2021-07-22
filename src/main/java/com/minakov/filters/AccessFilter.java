package com.minakov.filters;

import com.minakov.entities.RoleEnum;
import com.minakov.entities.User;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a filter that controls access to specific URLS.
 */
public class AccessFilter implements Filter {

    private final List<AntPathRequestMatcher> outerPaths = new ArrayList<AntPathRequestMatcher>() {{
        add(new AntPathRequestMatcher("/login"));
        add(new AntPathRequestMatcher("/registration"));
        add(new AntPathRequestMatcher("/books"));
        add(new AntPathRequestMatcher("/user/{id}/blocked"));
        add(new AntPathRequestMatcher("/user/{id}/logout"));
    }};
    private final AntPathRequestMatcher adminPath = new AntPathRequestMatcher("/admin/**");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (isInnerPath(request) && user == null) { // unknown user on deep page
            response.sendRedirect("/library/login");
        } else if (!adminPath.matches(request) && user != null &&
                user.getRole().getName().equals(RoleEnum.ADMIN.name())) { // admin on not admin page
            response.sendRedirect("/library/admin/books");
        } else if (adminPath.matches(request) && user != null &&
                !user.getRole().getName().equals(RoleEnum.ADMIN.name())) { // not admin on admin page
            response.sendRedirect("/library/books");
        } else if (isInnerPath(request) && user != null &&
                user.getRole().getName().equals(RoleEnum.BLOCKED.name())) { // blocked user to deep page
            response.sendRedirect("/library/user/" + user.getId() + "/blocked");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isInnerPath(HttpServletRequest request) {
        return outerPaths.stream()
                .noneMatch(antPathRequestMatcher -> antPathRequestMatcher.matches(request));
    }
}
