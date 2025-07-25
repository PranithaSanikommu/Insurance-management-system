package com.project.insurance_claim_management.service;

import com.project.insurance_claim_management.model.User;

public interface UserService {
    void registerUser(User user);
    User findByEmail(String email);
}
