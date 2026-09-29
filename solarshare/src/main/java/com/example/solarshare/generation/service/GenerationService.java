package com.example.solarshare.generation.service;

import com.example.solarshare.generation.entity.GenerationLog;
import com.example.solarshare.generation.repository.GenerationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerationService {

    private final GenerationRepository repository;

    public GenerationService(GenerationRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public GenerationLog addGeneration(
            GenerationLog data) {

        if (data.getUnitsGenerated() == null ||
                data.getUnitsGenerated() <= 0) {

            throw new RuntimeException(
                    "Units generated must be greater than 0"
            );
        }

        return repository.save(data);
    }

    // READ ALL
    public List<GenerationLog> getAllGenerations() {

        return repository.findAll();
    }

    // READ ONE
    public GenerationLog getGenerationById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Generation record not found with id: "
                                        + id
                        )
                );
    }

    // UPDATE
    public GenerationLog updateGeneration(
            Long id,
            GenerationLog data) {

        GenerationLog existing =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Generation record not found with id: "
                                                + id
                                )
                        );

        if (data.getUnitsGenerated() == null ||
                data.getUnitsGenerated() <= 0) {

            throw new RuntimeException(
                    "Units generated must be greater than 0"
            );
        }

        existing.setGenerationDate(
                data.getGenerationDate()
        );

        existing.setUnitsGenerated(
                data.getUnitsGenerated()
        );

        return repository.save(existing);
    }

    // DELETE
    public void deleteGeneration(Long id) {

        if (!repository.existsById(id)) {

            throw new RuntimeException(
                    "Generation record not found with id: "
                            + id
            );
        }

        repository.deleteById(id);
    }
}