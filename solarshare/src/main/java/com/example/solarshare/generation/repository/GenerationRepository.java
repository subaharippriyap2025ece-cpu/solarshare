package com.example.solarshare.generation.repository;

import com.example.solarshare.generation.entity.GenerationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenerationRepository extends JpaRepository<GenerationLog, Long> {
}