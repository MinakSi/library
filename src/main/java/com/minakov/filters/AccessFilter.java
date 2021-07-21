package com.minakov.filters;

import com.minakov.entities.RoleEnum;
import com.minakov.entities.User;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccessFilter implements Filter {

    private final List<AntPathRequestMatcher> outerPaths = new ArrayList<AntPathRequestMatcher>() {{
        add(new AntPathRequestMatcher("/login"));
        add(new AntPathRequestMatcher("/registration"));
        add(new AntPathRequestMatcher("/books"));
    }};
    private final AntPathRequestMatcher adminPath = new AntPathRequestMatcher("/admin/**");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        initOuterPaths();
        Filter.super.init(filterConfig);
    }

    private void initOuterPaths() {
        outerPaths.add(new AntPathRequestMatcher("/login"));
        outerPaths.add(new AntPathRequestMatcher("/registration"));
        outerPaths.add(new AntPathRequestMatcher("/books"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (isInnerPath(request) && user == null) { // незареганный юзер на глубокий адрес
            response.sendRedirect("/library/login");
        } else if (!adminPath.matches(request) && user != null &&
                user.getRole().getName().equals(RoleEnum.ADMIN.name())) { // админ на неадминский адрес
            response.sendRedirect("/library/admin/books");
        } else if (adminPath.matches(request) && user != null &&
                !user.getRole().getName().equals(RoleEnum.ADMIN.name())) { // не админ на админский адрес
            response.sendRedirect("/library/books");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isInnerPath(HttpServletRequest request) {
        return outerPaths.stream()
                .noneMatch(antPathRequestMatcher -> antPathRequestMatcher.matches(request));
//        for (AntPathRequestMatcher matcher : outerPaths) {
//            if (matcher.matches(request)) {
//                return false;
//            }
//        }
//        return true;
    }
}
