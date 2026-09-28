package com.example.solarshare.netusage.controller;

import com.example.solarshare.netusage.entity.NetUsageSummary;
import com.example.solarshare.netusage.service.NetUsageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/net-usage")
public class NetUsageController {

    private final NetUsageService netUsageService;

    public NetUsageController(NetUsageService netUsageService) {
        this.netUsageService = netUsageService;
    }

    @GetMapping("/calculate")
    public double calculateNetExported(
            @RequestParam double generationShare,
            @RequestParam double consumption) {

        return netUsageService.calculateNetExported(
                generationShare,
                consumption
        );
    }

    @GetMapping("/monthly-summary")
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