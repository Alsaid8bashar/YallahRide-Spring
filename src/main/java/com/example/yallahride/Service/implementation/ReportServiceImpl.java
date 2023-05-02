package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Report;
import com.example.yallahride.Repository.ReportRepository;
import com.example.yallahride.Service.Interface.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Optional<Report> findReportById(Long id) {
        return reportRepository.findById(id);
    }

    @Override
    public List<Report> findAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public void deleteAllReports() {
        reportRepository.deleteAll();
    }

    @Override
    public void deleteReportById(Long id) {
        reportRepository.deleteById(id);
    }

    @Override
    public long getNumberOfReport() {
        return reportRepository.count();
    }
}
