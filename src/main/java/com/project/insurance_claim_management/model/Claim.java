package com.project.insurance_claim_management.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Personal Information
    private String name;
    private String gender;
    private String mobile;
    private String email;
    private String aadharNo;
    private String address;

    // Claim Details
    private String reason;
    private Double claimAmount;
    private LocalDate claimDate;
    private String status = "Pending";

    // Policy Reference (Optional if using userPolicy)
    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    // UserPolicy Reference (for access to user + purchase date)
    @ManyToOne
    @JoinColumn(name = "user_policy_id")
    private UserPolicy userPolicy;

    // Constructors
    public Claim() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAadharNo() { return aadharNo; }
    public void setAadharNo(String aadharNo) { this.aadharNo = aadharNo; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Double getClaimAmount() { return claimAmount; }
    public void setClaimAmount(Double claimAmount) { this.claimAmount = claimAmount; }

    public LocalDate getClaimDate() { return claimDate; }
    public void setClaimDate(LocalDate claimDate) { this.claimDate = claimDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Policy getPolicy() { return policy; }
    public void setPolicy(Policy policy) { this.policy = policy; }
 

    public UserPolicy getUserPolicy() { return userPolicy; }
    public void setUserPolicy(UserPolicy userPolicy) { this.userPolicy = userPolicy; }
}
