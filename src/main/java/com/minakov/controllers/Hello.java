package com.minakov.controllers;

import com.minakov.entities.User;
import com.minakov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Hello {

    private final UserService userService;

    @Autowired
    public Hello(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        User userFormBean = new User();
        userFormBean.setLogin("this is login!!!!!!!!!!!!!!!!!");
        model.addAttribute("user", userFormBean);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Model model, @ModelAttribute User userForm){
//        userForm = (User) model.getAttribute("userForm");
        System.out.println(userForm.getPhone());
//        userService.saveUser(userForm);
        System.out.println("sosi");
        return "/successRegistration";
    }
}
