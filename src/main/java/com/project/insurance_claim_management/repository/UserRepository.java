package com.project.insurance_claim_management.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.insurance_claim_management.model.User;

import java.util.Optional;

	public interface UserRepository extends JpaRepository<User, Long> {
	    Optional<User> findByEmail(String email);
	    
	    
	}

