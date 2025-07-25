package com.project.insurance_claim_management.controller;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String showDashboard(Authentication authentication) {
        if (authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "admin-dashboard";  // your admin dashboard html page name
        }
        return "dashboard";  // normal user dashboard
    }}
