package com.system.saferide.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.saferide.dto.ReportRequest;
import com.system.saferide.model.Report;
import com.system.saferide.model.ReportType;
import com.system.saferide.repository.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repo;

    public Report createReport(ReportRequest req) {
        Report r = new Report();
        r.setLatitude(req.getLatitude());
        r.setLongitude(req.getLongitude());
        r.setType(req.getType());
        r.setDescription(req.getDescription());
        r.setTimestamp(LocalDateTime.now());
        return repo.save(r);
    }

    public List<Report> getReports(ReportType type) {
        if (type == null)
            return repo.findAll();
        return repo.findByType(type);
    }
}
