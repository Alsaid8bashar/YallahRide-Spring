package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Report.ReportTitle;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.ReportTitleRepository;
import com.example.yallahride.Service.Interface.ReportTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportTitleServiceImpl implements ReportTitleService {

    @Autowired
    ReportTitleRepository reportTitleRepository;

    static ReportTitle unwrapReportTitle(Optional<ReportTitle> ReportTitle, Long id) {
        if (ReportTitle.isPresent()) return ReportTitle.get();
        else throw new EntityNotFoundException(id, ReportTitle.class);
    }
    @Override
    public ReportTitle saveReportTitle(ReportTitle reportTitle) {
        return reportTitleRepository.save(reportTitle);
    }

    @Override
    public ReportTitle findReportTitleById(Long id) {
        return unwrapReportTitle(reportTitleRepository.findById(id),id);
    }

    @Override
    public List<ReportTitle> findAllReportTitles() {
        return reportTitleRepository.findAll();
    }

    @Override
    public ReportTitle updateReportTitle(ReportTitle ReportTitle) {
        return reportTitleRepository.save(ReportTitle);
    }

    @Override
    public void deleteAllReportTitles() {
        reportTitleRepository.deleteAll();
    }


    @Override
    public void deleteReportTitleById(Long id) {
        findReportTitleById(id);
        reportTitleRepository.deleteById(id);
    }

    @Override
    public List<ReportTitle> getAllUserReportsTitles() {
        return reportTitleRepository.getAllUserReportsTitles();
    }

    @Override
    public List<ReportTitle> getAllRideReportsTitles() {
        return reportTitleRepository.getAllRideReportsTitles();
    }
}
