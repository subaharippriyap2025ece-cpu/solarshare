package com.example.solarshare.consumption.repository;

import com.example.solarshare.consumption.entity.ConsumptionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ConsumptionRepository extends JpaRepository<ConsumptionLog, Long> {

    List<ConsumptionLog> findByHouseholdIdAndConsumptionDateBetween(
            Long householdId,
            LocalDate startDate,
            LocalDate endDate
    );
}