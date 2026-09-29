package com.example.solarshare.generation.controller;

import com.example.solarshare.generation.entity.GenerationLog;
import com.example.solarshare.generation.service.GenerationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generations")
@CrossOrigin
public class GenerationController {

    private final GenerationService service;

    public GenerationController(GenerationService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public GenerationLog addGeneration(
            @RequestBody GenerationLog data) {

        return service.addGeneration(data);
    }

    // READ ALL
    @GetMapping
    public List<GenerationLog> getAllGenerations() {

        return service.getAllGenerations();
    }

    // READ ONE
    @GetMapping("/{id}")
    public GenerationLog getGenerationById(
            @PathVariable Long id) {

        return service.getGenerationById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public GenerationLog updateGeneration(
            @PathVariable Long id,
            @RequestBody GenerationLog data) {

        return service.updateGeneration(id, data);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteGeneration(
            @PathVariable Long id) {

        service.deleteGeneration(id);

        return "Generation record deleted successfully";
    }
}