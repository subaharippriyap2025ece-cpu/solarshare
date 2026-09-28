package com.example.solarshare.household.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "household")
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long householdId;

    private String householdName;

    private String address;

    private Double allocationRatio;

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public String getHouseholdName() {
        return householdName;
    }

    public void setHouseholdName(String householdName) {
        this.householdName = householdName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAllocationRatio() {
        return allocationRatio;
    }

    public void setAllocationRatio(Double allocationRatio) {
        this.allocationRatio = allocationRatio;
    }
}