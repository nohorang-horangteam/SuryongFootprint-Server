package com.nohorang.suryongfootprint.repository;

import com.nohorang.suryongfootprint.model.Approval;
import com.nohorang.suryongfootprint.model.Count;
import com.nohorang.suryongfootprint.model.Report;
import com.nohorang.suryongfootprint.model.ReportId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, ReportId> {
    List<Report> findByCount(Count count);
}