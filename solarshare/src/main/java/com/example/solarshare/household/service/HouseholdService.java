package com.example.solarshare.household.service;

import com.example.solarshare.household.entity.Household;
import com.example.solarshare.household.repository.HouseholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseholdService {

    private final HouseholdRepository householdRepository;

    public HouseholdService(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    // Create household with validation
    public Household createHousehold(Household household) {

        // Check individual allocation ratio
        if (household.getAllocationRatio() == null ||
                household.getAllocationRatio() <= 0 ||
                household.getAllocationRatio() > 100) {

            throw new RuntimeException(
                    "Allocation ratio must be greater than 0 and not exceed 100"
            );
        }

        // Calculate current total allocation
        double currentTotal = householdRepository.findAll()
                .stream()
                .mapToDouble(Household::getAllocationRatio)
                .sum();

        // Check whether new household exceeds 100%
        if (currentTotal + household.getAllocationRatio() > 100) {

            throw new RuntimeException(
                    "Total household allocation ratio cannot exceed 100%"
            );
        }

        return householdRepository.save(household);
    }

    // Get all households
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    // Get household by ID
    public Optional<Household> getHouseholdById(Long id) {
        return householdRepository.findById(id);
    }

    // Delete household
    public void deleteHousehold(Long id) {
        householdRepository.deleteById(id);
    }
}