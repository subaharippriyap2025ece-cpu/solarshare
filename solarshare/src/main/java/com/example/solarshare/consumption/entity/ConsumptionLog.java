package com.example.solarshare.consumption.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "consumption_log")
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consumptionId;

    private LocalDate consumptionDate;

    private Long householdId;

    private Double unitsConsumed;

    public Long getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(Long consumptionId) {
        this.consumptionId = consumptionId;
    }

    public LocalDate getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(LocalDate consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public Double getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(Double unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }
}