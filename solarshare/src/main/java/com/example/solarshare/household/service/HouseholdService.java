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

    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    public Optional<Household> getHouseholdById(Long id) {
        return householdRepository.findById(id);
    }

    public void deleteHousehold(Long id) {
        householdRepository.deleteById(id);
    }
}