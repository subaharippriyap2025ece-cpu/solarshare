package com.example.solarshare.netusage.service;

import com.example.solarshare.consumption.entity.ConsumptionLog;
import com.example.solarshare.consumption.repository.ConsumptionRepository;
import com.example.solarshare.generation.entity.GenerationLog;
import com.example.solarshare.generation.repository.GenerationRepository;
import com.example.solarshare.household.entity.Household;
import com.example.solarshare.household.repository.HouseholdRepository;
import com.example.solarshare.netusage.entity.NetUsageSummary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class NetUsageService {

    private final HouseholdRepository householdRepository;
    private final GenerationRepository generationRepository;
    private final ConsumptionRepository consumptionRepository;

    public NetUsageService(
            HouseholdRepository householdRepository,
            GenerationRepository generationRepository,
            ConsumptionRepository consumptionRepository) {

        this.householdRepository = householdRepository;
        this.generationRepository = generationRepository;
        this.consumptionRepository = consumptionRepository;
    }

    public double calculateNetExported(
            double generationShare,
            double consumption) {

        double netExported = generationShare - consumption;

        if (netExported < 0) {
            netExported = 0;
        }

        return netExported;
    }

    public NetUsageSummary getMonthlySummary(
            Long householdId,
            int year,
            int month) {

        Household household = householdRepository.findById(householdId)
                .orElseThrow(() ->
                        new RuntimeException("Household not found"));

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<GenerationLog> generations =
                generationRepository.findByGenerationDateBetween(
                        startDate,
                        endDate);

        List<ConsumptionLog> consumptions =
                consumptionRepository
                        .findByHouseholdIdAndConsumptionDateBetween(
                                householdId,
                                startDate,
                                endDate);

        double totalGeneration = 0;

        for (GenerationLog generation : generations) {
            totalGeneration += generation.getUnitsGenerated();
        }

        double generationShare =
                totalGeneration
                        * household.getAllocationRatio()
                        / 100.0;

        double totalConsumption = 0;

        for (ConsumptionLog consumption : consumptions) {
            totalConsumption += consumption.getUnitsConsumed();
        }

        double netExported =
                calculateNetExported(
                        generationShare,
                        totalConsumption);

        NetUsageSummary summary = new NetUsageSummary();

        summary.setHouseholdId(
                household.getHouseholdId());

        summary.setHouseholdName(
                household.getHouseholdName());

        summary.setMonth(yearMonth);

        summary.setTotalGenerationShare(
                generationShare);

        summary.setTotalConsumption(
                totalConsumption);

        summary.setNetExported(
                netExported);

        return summary;
    }
}