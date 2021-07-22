package com.minakov.controllers;

import com.minakov.dto.UserDto;
import com.minakov.entities.User;
import com.minakov.exceptions.ApplicationException;
import com.minakov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * This controller describes actions that should be performed during
 * login or registration
 * This controller is for users with roles 'CUSTOMER', 'BLOCKED', unregistered users
 */
@Controller
public class AuthorizationController {

    private final UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLogin(HttpSession session, Model model) {
        model.addAttribute("user", new User());
        return "authorization/login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, Model model, @ModelAttribute("user") User user) {
        if (!userService.existsByLogin(user.getLogin())) {
            model.addAttribute("error", "There is no user with login '" + user.getLogin() + "'.");
            return "authorization/login";
        }
        if (!userService.getUserByLogin(user.getLogin()).getPassword().equals(user.getPassword())) {
            model.addAttribute("error", "Incorrect password");
            model.addAttribute("user", user);
            return "authorization/login";
        }
        session.setAttribute("user", userService.getUserByLogin(user.getLogin()));
        return "redirect:/books";
    }

    @GetMapping("/registration")
    public String showRegistration(HttpSession session, Model model) {
        model.addAttribute("user", new UserDto());
        return "authorization/registration";
    }

    @PostMapping("/registration")
    public String registerUser(HttpSession session, Model model, @ModelAttribute("user") @Valid UserDto userDto,
                               Errors errors) {
        User user;
        try {
            user = userService.registerNewUser(userDto);
        } catch (ApplicationException e) {
            model.addAttribute("user", userDto);
            return "authorization/registration";
        }
        session.setAttribute("user", user);
        return "redirect:/books";
    }
}
