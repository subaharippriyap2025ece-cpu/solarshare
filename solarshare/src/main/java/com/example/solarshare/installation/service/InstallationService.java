package com.example.solarshare.installation.service;

import com.example.solarshare.installation.entity.Installation;
import com.example.solarshare.installation.repository.InstallationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstallationService {

    private final InstallationRepository installationRepository;

    public InstallationService(InstallationRepository installationRepository) {
        this.installationRepository = installationRepository;
    }

    public Installation createInstallation(Installation installation) {
        return installationRepository.save(installation);
    }

    public List<Installation> getAllInstallations() {
        return installationRepository.findAll();
    }

    public Optional<Installation> getInstallationById(Long id) {
        return installationRepository.findById(id);
    }

    public void deleteInstallation(Long id) {
        installationRepository.deleteById(id);
    }
}