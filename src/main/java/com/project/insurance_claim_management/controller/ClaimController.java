package com.project.insurance_claim_management.controller;

import org.springframework.beans.factory.annotation.Autowired;


import com.project.insurance_claim_management.model.Claim;
import com.project.insurance_claim_management.model.Policy;
import com.project.insurance_claim_management.model.UserPolicy;
import com.project.insurance_claim_management.repository.ClaimRepository;
import com.project.insurance_claim_management.repository.PolicyRepository;
import com.project.insurance_claim_management.repository.UserPolicyRepository;
import com.project.insurance_claim_management.service.ClaimService;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/claims")
public class ClaimController {
	@Autowired 
	private UserPolicyRepository userPolicyRepository; 
    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private ClaimService claimService;

    // Show Claim Form
    @GetMapping("/create")
    public String showClaimForm(Model model) {
        model.addAttribute("claim", new Claim());
        List<Policy> policies = policyRepository.findAll();
        model.addAttribute("policies", policies);
        return "claimForm";
    }
    @PostMapping("/submitClaim")
    public String submitClaim(@ModelAttribute Claim claim,
                              @RequestParam("policyId") Long policyId,
                              Principal principal) {
        
        // 1. Get logged-in user's email
        String email = principal.getName();
        claim.setEmail(email);

        // 2. Set selected policy
        Policy policy = policyRepository.findById(policyId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid policy ID"));
        claim.setPolicy(policy);

        // ✅ 3. Set user policy (VERY IMPORTANT!)
        Optional<UserPolicy> userPolicyOpt = userPolicyRepository.findByEmailAndPolicyId(email, policyId);
        userPolicyOpt.ifPresent(claim::setUserPolicy); // this sets the foreign key `user_policy_id`

        // 4. Set date and status
        claim.setClaimDate(LocalDate.now());
        claim.setStatus("Pending");

        // 5. Save
        claimRepository.save(claim);

        return "redirect:/claims/success";
    }


    // Show Success Page
    @GetMapping("/success")
    public String showSuccessPage() {
        
        return "claimSuccess";
    }

    // Show Claim History
    @GetMapping("/history")
    public String viewClaimHistory(Model model, Principal principal) {
        System.out.println("✅ Mapping '/claims/history' reached");

        if (principal == null) {
            System.out.println("❌ Principal is null — User not logged in");
            return "redirect:/login";
        }

        String email = principal.getName();
        System.out.println("📧 Logged-in user's email: " + email);

        List<Claim> claims = claimService.getClaimsByEmail(email);
        System.out.println("🧾 Number of claims fetched: " + claims.size());

        model.addAttribute("claims", claims);
        return "claimHistory";
        
    }
    @GetMapping("/delete")
    public String deleteClaim(@RequestParam("id") Long id, Principal principal) {
        Optional<Claim> optionalClaim = claimRepository.findById(id);

        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            String loggedInEmail = principal.getName();

            if (claim.getEmail().equals(loggedInEmail)) {
                claimRepository.deleteById(id);
            }
        }
        return "redirect:/claims/history";
    }
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model, Principal principal) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid claim ID"));
        
        // Optionally check if this claim belongs to logged-in user
        if (!claim.getEmail().equals(principal.getName())) {
            return "redirect:/access-denied"; // or show error
        }

        model.addAttribute("claim", claim);
        model.addAttribute("policies", policyRepository.findAll());
        return "editclaim";  // You need to create this HTML
    }
    @PostMapping("/update")
    public String updateClaim(@ModelAttribute("claim") Claim claimFromForm, Principal principal) {
        Claim existingClaim = claimRepository.findById(claimFromForm.getId())
                .orElseThrow(() -> new IllegalArgumentException("Claim not found"));

        if (!existingClaim.getEmail().equals(principal.getName())) {
            return "redirect:/access-denied";
        }

        existingClaim.setReason(claimFromForm.getReason());
        existingClaim.setClaimAmount(claimFromForm.getClaimAmount());
        existingClaim.setPolicy(claimFromForm.getPolicy()); // if you allow policy change
        claimRepository.save(existingClaim);
        
        return "redirect:/claims/history";
    }

}