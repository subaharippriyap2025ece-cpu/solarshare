package com.example.solarshare.generation.controller;

import com.example.solarshare.generation.entity.GenerationLog;
import com.example.solarshare.generation.service.GenerationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generations")
@Tag(name = "Generation", description = "APIs for managing daily solar generation")
public class GenerationController {

    private final GenerationService generationService;

    public GenerationController(GenerationService generationService) {
        this.generationService = generationService;
    }

    @PostMapping
    @Operation(summary = "Create a generation log")
    public GenerationLog createGeneration(@RequestBody GenerationLog generationLog) {
        return generationService.createGeneration(generationLog);
    }

    @GetMapping
    @Operation(summary = "Get all generation logs")
    public List<GenerationLog> getAllGenerations() {
        return generationService.getAllGenerations();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get generation log by ID")
    public ResponseEntity<GenerationLog> getGenerationById(@PathVariable Long id) {
        return generationService.getGenerationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete generation log")
    public ResponseEntity<Void> deleteGeneration(@PathVariable Long id) {
        generationService.deleteGeneration(id);
        return ResponseEntity.noContent().build();
    }
}