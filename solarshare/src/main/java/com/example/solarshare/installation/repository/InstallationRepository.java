package com.example.solarshare.installation.repository;

import com.example.solarshare.installation.entity.Installation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallationRepository extends JpaRepository<Installation, Long> {
}