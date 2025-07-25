package com.project.insurance_claim_management.service;


//PolicyService
import com.project.insurance_claim_management.model.Policy;
import com.project.insurance_claim_management.model.UserPolicy;
import com.project.insurance_claim_management.repository.PolicyRepository;
import com.project.insurance_claim_management.repository.UserPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class PolicyService {

    @Autowired
    private UserPolicyRepository userPolicyRepository;

    @Autowired
    private PolicyRepository policyRepository;
    public Optional<Policy> getPolicyByEmail(String email) {
        return userPolicyRepository.findFirstByEmail(email)
                                   .map(UserPolicy::getPolicy); // ✅ now this will work
    }


    public Optional<Policy> getPolicyById(Long id) {
        return policyRepository.findById(id);
    }
}


