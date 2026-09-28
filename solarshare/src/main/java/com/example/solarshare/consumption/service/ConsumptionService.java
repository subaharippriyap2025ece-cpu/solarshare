package com.example.solarshare.consumption.service;

import com.example.solarshare.consumption.entity.ConsumptionLog;
import com.example.solarshare.consumption.repository.ConsumptionRepository;
import com.example.solarshare.household.repository.HouseholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumptionService {

    private final ConsumptionRepository consumptionRepository;
    private final HouseholdRepository householdRepository;

    public ConsumptionService(
            ConsumptionRepository consumptionRepository,
            HouseholdRepository householdRepository) {

        this.consumptionRepository = consumptionRepository;
        this.householdRepository = householdRepository;
    }

    // Create consumption log with validation
    public ConsumptionLog createConsumption(ConsumptionLog consumptionLog) {

        // Validate consumption units
        if (consumptionLog.getUnitsConsumed() == null ||
                consumptionLog.getUnitsConsumed() <= 0) {

            throw new RuntimeException(
                    "Units consumed must be greater than 0"
            );
        }

        // Validate household
        if (!householdRepository.existsById(
                consumptionLog.getHouseholdId())) {

            throw new RuntimeException(
                    "Household not found"
            );
        }

        return consumptionRepository.save(consumptionLog);
    }

    // Get all consumption logs
    public List<ConsumptionLog> getAllConsumptions() {
        return consumptionRepository.findAll();
    }

    // Get consumption by ID
    public Optional<ConsumptionLog> getConsumptionById(Long id) {
        return consumptionRepository.findById(id);
    }

    // Delete consumption
    public void deleteConsumption(Long id) {
        consumptionRepository.deleteById(id);
    }
}