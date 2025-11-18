package com.system.saferide.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.system.saferide.model.Report;
import com.system.saferide.repository.ReportRepository;

@Controller
public class PageController {

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/report")
    public String showReportForm(Model model) {
        model.addAttribute("report", new Report());
        return "report";
    }

    @PostMapping("/report")
    public String submitReport(@ModelAttribute Report report) {
        report.setTimestamp(LocalDateTime.now());
        reportRepository.save(report);
        return "redirect:/report?success";
    }
}
