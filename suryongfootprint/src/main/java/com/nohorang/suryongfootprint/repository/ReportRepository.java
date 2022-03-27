package com.nohorang.suryongfootprint.repository;

import com.nohorang.suryongfootprint.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, ReportId> {
    Optional<Report> findByCountAndUser(Count count, User user);
}