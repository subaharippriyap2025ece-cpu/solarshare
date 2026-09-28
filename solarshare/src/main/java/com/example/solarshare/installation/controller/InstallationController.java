package com.example.solarshare.installation.controller;

import com.example.solarshare.installation.entity.Installation;
import com.example.solarshare.installation.service.InstallationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/installations")
@Tag(name = "Installation", description = "APIs for managing solar installations")
public class InstallationController {

    private final InstallationService installationService;

    public InstallationController(InstallationService installationService) {
        this.installationService = installationService;
    }

    @PostMapping
    @Operation(summary = "Create a new installation")
    public Installation createInstallation(@RequestBody Installation installation) {
        return installationService.createInstallation(installation);
    }

    @GetMapping
    @Operation(summary = "Get all installations")
    public List<Installation> getAllInstallations() {
        return installationService.getAllInstallations();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get installation by ID")
    public ResponseEntity<Installation> getInstallationById(@PathVariable Long id) {
        return installationService.getInstallationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete installation")
    public ResponseEntity<Void> deleteInstallation(@PathVariable Long id) {
        installationService.deleteInstallation(id);
        return ResponseEntity.noContent().build();
    }
}