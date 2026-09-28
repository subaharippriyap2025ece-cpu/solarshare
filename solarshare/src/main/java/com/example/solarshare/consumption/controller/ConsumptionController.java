package com.example.solarshare.consumption.controller;

import com.example.solarshare.consumption.entity.ConsumptionLog;
import com.example.solarshare.consumption.service.ConsumptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumptions")
@Tag(name = "Consumption", description = "APIs for managing household consumption")
public class ConsumptionController {

    private final ConsumptionService consumptionService;

    public ConsumptionController(ConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
    }

    @PostMapping
    @Operation(summary = "Create a consumption log")
    public ConsumptionLog createConsumption(@RequestBody ConsumptionLog consumptionLog) {
        return consumptionService.createConsumption(consumptionLog);
    }

    @GetMapping
    @Operation(summary = "Get all consumption logs")
    public List<ConsumptionLog> getAllConsumptions() {
        return consumptionService.getAllConsumptions();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get consumption log by ID")
    public ResponseEntity<ConsumptionLog> getConsumptionById(@PathVariable Long id) {
        return consumptionService.getConsumptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete consumption log")
    public ResponseEntity<Void> deleteConsumption(@PathVariable Long id) {
        consumptionService.deleteConsumption(id);
        return ResponseEntity.noContent().build();
    }
}