package com.example.solarshare.generation.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "generation_log")
public class GenerationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long generationId;

    private LocalDate generationDate;

    private Double unitsGenerated;

    public Long getGenerationId() {
        return generationId;
    }

    public void setGenerationId(Long generationId) {
        this.generationId = generationId;
    }

    public LocalDate getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(LocalDate generationDate) {
        this.generationDate = generationDate;
    }

    public Double getUnitsGenerated() {
        return unitsGenerated;
    }

    public void setUnitsGenerated(Double unitsGenerated) {
        this.unitsGenerated = unitsGenerated;
    }
}