package com.project.insurance_claim_management.controller;



import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.project.insurance_claim_management.repository.ClaimRepository;
import com.project.insurance_claim_management.repository.UserPolicyRepository;
import com.project.insurance_claim_management.model.Claim;
import com.project.insurance_claim_management.model.UserPolicy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class AdminController {

    @Autowired
    private ClaimRepository claimRepo;
    
    @Autowired
    private UserPolicyRepository userPolicyRepo;
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard() {
        return "admin-dashboard"; // ✅ matches the template file name
    }

    @GetMapping("/admin/claims")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAllClaims(Model model) {
        List<Claim> allClaims = claimRepo.findAll();
        model.addAttribute("claims", allClaims);
        return "admin-claims"; // Create this new Thymeleaf page
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/claims/approve")
    public String approveClaim(@RequestParam Long claimId) {
        Claim claim = claimRepo.findById(claimId).orElseThrow();
        claim.setStatus("Approved");
        claimRepo.save(claim);
        return "redirect:/admin/claims";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/claims/reject")
    public String rejectClaim(@RequestParam Long claimId) {
        Claim claim = claimRepo.findById(claimId).orElseThrow();
        claim.setStatus("Rejected");
        claimRepo.save(claim);
        return "redirect:/admin/claims";
    }
    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String viewAllUsers(Model model) {
        List<UserPolicy> userPolicies = userPolicyRepo.findAll();  // Fetches all user-policy mappings
        model.addAttribute("userPolicies", userPolicies);
        return "admin-users"; // Name of the Thymeleaf template (you will create this)
    }


}