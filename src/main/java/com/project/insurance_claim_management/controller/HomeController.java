package com.project.insurance_claim_management.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String showHomePage() {
        return "home"; // maps to templates/home.html
    }
}
