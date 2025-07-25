package com.project.insurance_claim_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String benefits;
    private double premium;
    private double amount;
    private double coverage;
    private int duration;
   

   
    public Policy() {
    }

    public Policy(Long id, String name, String description, String benefits,
                  double premium, double amount, double coverage, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.benefits = benefits;
        this.premium = premium;
        this.amount = amount;
        this.coverage = coverage;
        this.duration = duration;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getBenefits() { return benefits; }

    public void setBenefits(String benefits) { this.benefits = benefits; }

    public double getPremium() { return premium; }

    public void setPremium(double premium) { this.premium = premium; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public double getCoverage() { return coverage; }

    public void setCoverage(double coverage) { this.coverage = coverage; }

    public int getDuration() { return duration; }

    public void setDuration(int duration) { this.duration = duration; }
}
