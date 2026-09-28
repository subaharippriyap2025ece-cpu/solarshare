package com.example.solarshare.netusage.entity;

import java.time.YearMonth;

public class NetUsageSummary {

    private Long householdId;
    private String householdName;
    private YearMonth month;
    private Double totalGenerationShare;
    private Double totalConsumption;
    private Double netExported;

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public String getHouseholdName() {
        return householdName;
    }

    public void setHouseholdName(String householdName) {
        this.householdName = householdName;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public Double getTotalGenerationShare() {
        return totalGenerationShare;
    }

    public void setTotalGenerationShare(Double totalGenerationShare) {
        this.totalGenerationShare = totalGenerationShare;
    }

    public Double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(Double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public Double getNetExported() {
        return netExported;
    }

    public void setNetExported(Double netExported) {
        this.netExported = netExported;
    }
}