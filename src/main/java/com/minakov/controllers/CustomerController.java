package com.minakov.controllers;

import com.minakov.entities.User;
import com.minakov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class CustomerController {

    private final UserService userService;

    @Autowired
    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String showProfile(HttpSession session, Model model, @PathVariable("id") Long userId) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null || !Objects.equals(sessionUser.getId(), userId)) {
            return "redirect:/books";
        }
        return "customer/user/profile";
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
