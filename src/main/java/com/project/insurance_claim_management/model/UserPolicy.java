
package com.project.insurance_claim_management.model;
import jakarta.persistence.*;
import java.time.LocalDate;



@Entity
public class UserPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    private LocalDate purchaseDate;
    private int durationYears;

    // --- Constructors ---
    public UserPolicy() {}

    public UserPolicy(String email, Policy policy, LocalDate purchaseDate,int durationYears) {
        this.email = email;
        this.policy = policy;
        this.purchaseDate = purchaseDate;
        this.durationYears=durationYears;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    public int getDurationYears() {
        return durationYears;
    }


    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "UserPolicy{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", policy=" + policy +
                ", purchaseDate=" + purchaseDate +
                ", durationYears=" + durationYears +
                '}';
    }
}