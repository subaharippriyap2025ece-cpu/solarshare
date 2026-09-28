package com.example.solarshare.generation.repository;

import com.example.solarshare.generation.entity.GenerationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GenerationRepository extends JpaRepository<GenerationLog, Long> {

    List<GenerationLog> findByGenerationDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );
}