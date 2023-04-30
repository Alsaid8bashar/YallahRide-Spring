package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    void saveReport(Report report);

    Optional<Report> findReportById(Long id);

    List<Report> findAllReports();

    void deleteAllReports();

    void deleteReportById(Long id);

    long getNumberOfReport();
}
