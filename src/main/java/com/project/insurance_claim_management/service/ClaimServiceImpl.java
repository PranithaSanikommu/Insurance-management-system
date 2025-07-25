package com.project.insurance_claim_management.service;

import com.project.insurance_claim_management.model.Claim;
import com.project.insurance_claim_management.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Override
    public void saveClaim(Claim claim) {
        claimRepository.save(claim);
    }

    @Override
    public List<Claim> getClaimsByEmail(String email) {
        return claimRepository.findByEmail(email);
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
}
