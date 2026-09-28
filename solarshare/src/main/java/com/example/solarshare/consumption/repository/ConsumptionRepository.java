package com.example.solarshare.consumption.repository;

import com.example.solarshare.consumption.entity.ConsumptionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumptionRepository extends JpaRepository<ConsumptionLog, Long> {
}
