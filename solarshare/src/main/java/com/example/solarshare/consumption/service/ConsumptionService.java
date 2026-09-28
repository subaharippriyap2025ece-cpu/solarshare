package com.example.solarshare.consumption.service;

import com.example.solarshare.consumption.entity.ConsumptionLog;
import com.example.solarshare.consumption.repository.ConsumptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumptionService {

    private final ConsumptionRepository consumptionRepository;

    public ConsumptionService(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }

    public ConsumptionLog createConsumption(ConsumptionLog consumptionLog) {
        return consumptionRepository.save(consumptionLog);
    }

    public List<ConsumptionLog> getAllConsumptions() {
        return consumptionRepository.findAll();
    }

    public Optional<ConsumptionLog> getConsumptionById(Long id) {
        return consumptionRepository.findById(id);
    }

    public void deleteConsumption(Long id) {
        consumptionRepository.deleteById(id);
    }
}