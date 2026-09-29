package com.example.solarshare.generation.repository;

import com.example.solarshare.generation.entity.GenerationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GenerationRepository
        extends JpaRepository<GenerationLog, Long> {

    List<GenerationLog> findByGenerationDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );
}