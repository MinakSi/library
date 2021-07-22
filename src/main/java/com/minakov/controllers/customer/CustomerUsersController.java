package com.minakov.controllers.customer;

import com.minakov.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * This controller describes actions that should be performed when
 * URL matches /user/**
 * This controller is for users with roles 'CUSTOMER', 'BLOCKED'
 */
@Controller
@RequestMapping("/user")
public class CustomerUsersController {

    @GetMapping("/{id}")
    public String showProfile(HttpSession session, Model model, @PathVariable("id") Long userId) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null || !Objects.equals(sessionUser.getId(), userId)) {
            return "redirect:/books";
        }
        return "customer/user/profile";
    }

    @GetMapping("/{id}/blocked")
    public String showBlockedPage(HttpSession session, Model model, @PathVariable("id") Long userId) {
        return "blockedPage";
    }

    @GetMapping("/{id}/logout")
    public String logout(HttpSession session, Model model, @PathVariable("id") Long userId) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null || !Objects.equals(sessionUser.getId(), userId)) {
            return "redirect:/books";
        }
        session.removeAttribute("user");
        return "redirect:/books";
    }
}
