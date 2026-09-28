package com.example.solarshare.household.controller;

import com.example.solarshare.household.entity.Household;
import com.example.solarshare.household.service.HouseholdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/households")
@Tag(name = "Household", description = "APIs for managing households")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    @Operation(summary = "Create a new household")
    public Household createHousehold(@RequestBody Household household) {
        return householdService.createHousehold(household);
    }

    @GetMapping
    @Operation(summary = "Get all households")
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get household by ID")
    public ResponseEntity<Household> getHouseholdById(@PathVariable Long id) {
        return householdService.getHouseholdById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete household")
    public ResponseEntity<Void> deleteHousehold(@PathVariable Long id) {
        householdService.deleteHousehold(id);
        return ResponseEntity.noContent().build();
    }
}