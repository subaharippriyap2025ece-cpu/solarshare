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
import java.util.ArrayList;
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


    // =========================================================
    // GET SUMMARY FOR ONE HOUSEHOLD
    // =========================================================

    public NetUsageSummary getMonthlySummary(
            Long householdId,
            int year,
            int month) {

        Household household =
                householdRepository.findById(householdId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Household not found"
                                )
                        );


        YearMonth yearMonth =
                YearMonth.of(year, month);


        LocalDate startDate =
                yearMonth.atDay(1);

        LocalDate endDate =
                yearMonth.atEndOfMonth();


        // =========================
        // GENERATION
        // =========================

        List<GenerationLog> generations =
                generationRepository
                        .findByGenerationDateBetween(
                                startDate,
                                endDate
                        );


        double totalGeneration = 0;


        for (GenerationLog generation : generations) {

            if (generation.getUnitsGenerated() == null ||
                    generation.getUnitsGenerated() < 0) {

                throw new RuntimeException(
                        "Invalid generation data found"
                );
            }

            totalGeneration +=
                    generation.getUnitsGenerated();
        }


        // =========================
        // GENERATION SHARE
        // =========================

        double allocationRatio =
                household.getAllocationRatio();


        double generationShare =
                totalGeneration
                        * allocationRatio
                        / 100.0;


        if (generationShare > totalGeneration) {

            throw new RuntimeException(
                    "Household generation share cannot exceed total generation"
            );
        }


        // =========================
        // CONSUMPTION
        // =========================

        List<ConsumptionLog> consumptions =
                consumptionRepository
                        .findByHouseholdIdAndConsumptionDateBetween(
                                householdId,
                                startDate,
                                endDate
                        );


        double totalConsumption = 0;


        for (ConsumptionLog consumption : consumptions) {

            if (consumption.getUnitsConsumed() == null ||
                    consumption.getUnitsConsumed() < 0) {

                throw new RuntimeException(
                        "Invalid consumption data found"
                );
            }

            totalConsumption +=
                    consumption.getUnitsConsumed();
        }


        // =========================
        // NET ENERGY
        // =========================

        double netExported =
                calculateNetExported(
                        generationShare,
                        totalConsumption
                );


        // =========================
        // CREATE RESPONSE
        // =========================

        NetUsageSummary summary =
                new NetUsageSummary();


        summary.setHouseholdId(
                household.getHouseholdId()
        );


        summary.setHouseholdName(
                household.getHouseholdName()
        );


        summary.setMonth(
                yearMonth
        );


        summary.setTotalGenerationShare(
                generationShare
        );


        summary.setTotalConsumption(
                totalConsumption
        );


        summary.setNetExported(
                netExported
        );


        return summary;
    }


    // =========================================================
    // GET CURRENT MONTH SUMMARY FOR ALL HOUSEHOLDS
    // =========================================================

    public List<NetUsageSummary> getCurrentMonthSummary() {

        YearMonth currentMonth =
                YearMonth.now();


        int year =
                currentMonth.getYear();


        int month =
                currentMonth.getMonthValue();


        List<Household> households =
                householdRepository.findAll();


        List<NetUsageSummary> summaries =
                new ArrayList<>();


        for (Household household : households) {

            NetUsageSummary summary =
                    getMonthlySummary(
                            household.getHouseholdId(),
                            year,
                            month
                    );


            summaries.add(summary);
        }


        return summaries;
    }
}