package com.example.solarshare.household.repository;

import com.example.solarshare.household.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
}