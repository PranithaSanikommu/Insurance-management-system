package com.project.insurance_claim_management.repository;



import com.project.insurance_claim_management.model.Policy;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    // No need to redeclare findById(Long id) — it's already included by JpaRepository
}
