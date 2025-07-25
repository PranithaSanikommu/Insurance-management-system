package com.project.insurance_claim_management.service;

import com.project.insurance_claim_management.model.Claim;


import java.util.List;


public interface ClaimService {
    void saveClaim(Claim claim);
    List<Claim> getClaimsByEmail(String email);
    List<Claim> getAllClaims();
}
