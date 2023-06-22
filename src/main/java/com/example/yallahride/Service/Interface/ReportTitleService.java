package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Report.ReportTitle;

import java.util.List;

public interface ReportTitleService {
    ReportTitle saveReportTitle(ReportTitle ReportTitle);

    ReportTitle findReportTitleById(Long id);

    List<ReportTitle> findAllReportTitles();

    ReportTitle updateReportTitle(ReportTitle ReportTitle);

    void deleteAllReportTitles();

    void deleteReportTitleById(Long id);

    List<ReportTitle> getAllUserReportsTitles();

    List<ReportTitle> getAllRideReportsTitles();
}
