package com.example.solarshare.installation.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "installation")
public class Installation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long installationId;

    private LocalDate installationDate;

    private String panelType;

    private Double capacity;

    private String location;

    private String status;

    public Long getInstallationId() {
        return installationId;
    }

    public void setInstallationId(Long installationId) {
        this.installationId = installationId;
    }

    public LocalDate getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(LocalDate installationDate) {
        this.installationDate = installationDate;
    }

    public String getPanelType() {
        return panelType;
    }

    public void setPanelType(String panelType) {
        this.panelType = panelType;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}