package com.project.insurance_claim_management.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.insurance_claim_management.model.User;
import com.project.insurance_claim_management.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // add PasswordEncoder bean in SecurityConfig!

    @Override
    public void registerUser(User user) {
        // Encrypt password before saving
    	user.setRole("ROLE_USER");
        // Save user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
