package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Report.ReportRide;

import java.util.List;

public interface ReportRideService {
    ReportRide saveReportRide(ReportRide ReportRide);

    ReportRide findReportRideById(Long id);

    List<ReportRide> findAllReportRides();
    ReportRide updateReportRide(ReportRide ReportRide);

    void deleteAllReportRides();

    void deleteReportRideById(Long id);
}
