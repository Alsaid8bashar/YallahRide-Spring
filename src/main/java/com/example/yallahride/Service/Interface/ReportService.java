package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Report;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReportService {
    Report saveReport(Report report);

    Report findReportById(Long id);

    Collection<Report> findUserReports(Long userId);

    List<Report> findAllReports();

    void deleteAllReports();

    void deleteReportById(Long id);

    long getNumberOfReport();
}
