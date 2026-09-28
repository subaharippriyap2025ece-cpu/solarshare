package com.example.solarshare.generation.service;

import com.example.solarshare.generation.entity.GenerationLog;
import com.example.solarshare.generation.repository.GenerationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenerationService {

    private final GenerationRepository generationRepository;

    public GenerationService(GenerationRepository generationRepository) {
        this.generationRepository = generationRepository;
    }

    public GenerationLog createGeneration(GenerationLog generationLog) {
        return generationRepository.save(generationLog);
    }

    public List<GenerationLog> getAllGenerations() {
        return generationRepository.findAll();
    }

    public Optional<GenerationLog> getGenerationById(Long id) {
        return generationRepository.findById(id);
    }

    public void deleteGeneration(Long id) {
        generationRepository.deleteById(id);
    }
}