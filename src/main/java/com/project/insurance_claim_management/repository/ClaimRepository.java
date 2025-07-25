package com.project.insurance_claim_management.repository;

import com.project.insurance_claim_management.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByEmail(String email);
}
