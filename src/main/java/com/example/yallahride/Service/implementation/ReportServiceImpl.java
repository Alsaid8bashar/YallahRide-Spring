package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Report;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.ReportRepository;
import com.example.yallahride.Service.Interface.ReportService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    final private ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }


    @Override
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Report findReportById(Long id) {
        return unwrapReport(reportRepository.findById(id),id);
    }

    @Override
    public Collection<Report> findUserReports(Long userId) {
        return reportRepository.findUserReport(userId);
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
    static Report unwrapReport(Optional<Report> report, Long id) {
        if (report.isPresent()) return report.get();
        else throw new EntityNotFoundException(id, Report.class);
    }
}
