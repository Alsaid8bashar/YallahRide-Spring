package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Report.Report;
import com.example.yallahride.Entity.Report.ReportUser;

import java.util.List;

public interface ReportUserService {
    ReportUser saveReportUser(ReportUser ReportUser);

    ReportUser findReportUserById(Long id);

    List<ReportUser> findAllReportUsers();
    ReportUser updateReportUser(ReportUser ReportUser);

    void deleteAllReportUsers();

    void deleteReportUserById(Long id);
}
