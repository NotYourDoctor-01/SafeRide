
package com.system.saferide.repository;

import com.system.saferide.model.Report;
import com.system.saferide.model.ReportType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByType(ReportType type);
}
