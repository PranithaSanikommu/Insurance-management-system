

package com.project.insurance_claim_management.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.insurance_claim_management.model.Policy;
import com.project.insurance_claim_management.model.UserPolicy;
import com.project.insurance_claim_management.repository.PolicyRepository;
import com.project.insurance_claim_management.repository.UserPolicyRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Controller
public class PolicyController {

    @Autowired
    private PolicyRepository policyRepo;

    @Autowired
    private UserPolicyRepository userPolicyRepo;

    // View all policies and show user's purchased one if exists
    @GetMapping("/policies")
    public String viewPolicies(Model model, Principal principal) {
        String email = principal.getName();

        Optional<UserPolicy> purchasedPolicyOpt = userPolicyRepo.findFirstByEmail(email);  // ✅ Make sure this method exists

        List<Policy> allPolicies = policyRepo.findAll();

        List<Policy> availablePolicies = new ArrayList<>(allPolicies);
        purchasedPolicyOpt.ifPresent(userPolicy -> availablePolicies.remove(userPolicy.getPolicy()));

        // ✅ For Thymeleaf: show both
        purchasedPolicyOpt.ifPresent(userPolicy -> model.addAttribute("purchasedPolicy", userPolicy));
        model.addAttribute("availablePolicies", availablePolicies);

        return "policies";
    }


    @PostMapping("/buyPolicy")
    public String buyPolicy(@RequestParam Long policyId, Principal principal) {
        String email = principal.getName();

        // Check if user already selected a policy
        Optional<UserPolicy> existingPolicyOpt = userPolicyRepo.findFirstByEmail(email);
        if (existingPolicyOpt.isPresent()) {
            return "redirect:/policies?error=alreadySelected";
        }

        // Get the selected policy
        Policy policy = policyRepo.findById(policyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid policy ID"));

        // ✅ Get the duration from Policy (this works because you already have it)
        int durationYears = policy.getDuration();  // ✅ This is correct!

        // ✅ Create and save the UserPolicy with duration
        UserPolicy userPolicy = new UserPolicy(email, policy, LocalDate.now(), durationYears);
        userPolicyRepo.save(userPolicy);

        return "redirect:/policies?success=true";
    }

}

