package com.system.saferide.controller;

import com.system.saferide.model.Report;
import com.system.saferide.model.ReportType;
import com.system.saferide.repository.ReportRepository;
import com.system.saferide.dto.ReportRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/reports/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        System.out.println("DELETE called for ID = " + id);

        if (!reportRepository.existsById(id)) {
            System.out.println("ID NOT FOUND");
            return ResponseEntity.notFound().build();
        }

        reportRepository.deleteById(id);
        System.out.println("ID DELETED");
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/report")
    public String submitReport(@ModelAttribute Report report) {
        report.setTimestamp(LocalDateTime.now());
        reportRepository.save(report);
        return "redirect:/report?success=true";
    }

    @PostMapping("/reports/{id}/help")
    public ResponseEntity<Void> markHelpOnTheWay(@PathVariable Long id) {
        var optionalReport = reportRepository.findById(id);

        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            report.setHelpOnTheWay(true);
            reportRepository.save(report);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
