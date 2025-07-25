package com.project.insurance_claim_management.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.insurance_claim_management.model.UserPolicy;

public interface UserPolicyRepository extends JpaRepository<UserPolicy, Long> {
    Optional<UserPolicy> findFirstByEmail(String email);

Optional<UserPolicy> findByEmailAndPolicyId(String email, Long policyId);
}