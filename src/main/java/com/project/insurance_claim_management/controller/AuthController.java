package com.project.insurance_claim_management.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.project.insurance_claim_management.model.User;
import com.project.insurance_claim_management.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Show Registration Page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";  // maps to register.html
    }

    // Process Registration Form Submission
    @PostMapping("/register")
    public String processRegister(@ModelAttribute User user) {
        userService.registerUser(user);  // encrypt password and save user
        return "redirect:/login";         // after registration, redirect to login page
    }

    // Show Login Page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // maps to login.html
    }
  
    
}

