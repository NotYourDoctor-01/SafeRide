package com.system.saferide.controller;

import com.system.saferide.model.Report;
import com.system.saferide.repository.ReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/reports")
    public List<Report> getReports() {
        return reportRepository.findAll();
    }
}
