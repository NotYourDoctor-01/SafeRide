package com.system.saferide.controller;

import com.system.saferide.model.Report;
import com.system.saferide.model.ReportType;
import com.system.saferide.repository.ReportRepository;
import com.system.saferide.dto.ReportRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class ApiReportController {

    @Autowired
    private ReportRepository reportRepository;

    @PostMapping("/")
    public Report saveReport(@RequestBody ReportRequest req) {
        Report report = new Report();
        report.setLatitude(req.getLatitude());
        report.setLongitude(req.getLongitude());
        report.setType(req.getType());
        report.setDescription(req.getDescription());
        report.setTimestamp(LocalDateTime.now());

        return reportRepository.save(report);
    }
}
