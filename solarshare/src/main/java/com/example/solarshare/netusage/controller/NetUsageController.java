package com.example.solarshare.netusage.controller;

import com.example.solarshare.netusage.entity.NetUsageSummary;
import com.example.solarshare.netusage.service.NetUsageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/net-usage")
@Tag(
        name = "Net Usage",
        description = "APIs for calculating household net solar usage"
)
public class NetUsageController {

    private final NetUsageService netUsageService;

    public NetUsageController(NetUsageService netUsageService) {
        this.netUsageService = netUsageService;
    }

    @GetMapping("/calculate")
    @Operation(
            summary = "Calculate net exported units",
            description = "Calculates generation share minus household consumption. Result cannot be negative."
    )
    public double calculateNetExported(
            @RequestParam double generationShare,
            @RequestParam double consumption) {

        return netUsageService.calculateNetExported(
                generationShare,
                consumption
        );
    }

    @GetMapping("/monthly-summary")
    @Operation(
            summary = "Get monthly net usage summary",
            description = "Returns the monthly generation share, consumption and net exported units for a household."
    )
    public NetUsageSummary getMonthlySummary(
            @RequestParam Long householdId,
            @RequestParam int year,
            @RequestParam int month) {

        return netUsageService.getMonthlySummary(
                householdId,
                year,
                month
        );
    }
}