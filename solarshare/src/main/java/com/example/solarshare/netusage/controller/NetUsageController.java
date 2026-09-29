package com.example.solarshare.netusage.controller;

import com.example.solarshare.netusage.entity.NetUsageSummary;
import com.example.solarshare.netusage.service.NetUsageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/net-usage")
@CrossOrigin
public class NetUsageController {

    private final NetUsageService service;


    public NetUsageController(
            NetUsageService service) {

        this.service = service;
    }


    // =========================================================
    // CURRENT MONTH - ALL HOUSEHOLDS
    // =========================================================

    @GetMapping("/monthly-summary")
    public List<NetUsageSummary> getMonthlySummary() {

        return service.getCurrentMonthSummary();
    }
}