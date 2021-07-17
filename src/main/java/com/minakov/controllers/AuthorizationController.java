package com.minakov.controllers;

import com.minakov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorizationController {

    private final UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }


}
