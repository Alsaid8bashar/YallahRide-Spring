package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Report.ReportRide;
import com.example.yallahride.Entity.Report.ReportRide;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.ReportRideRepository;
import com.example.yallahride.Service.Interface.ReportRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportRideServiceImpl  implements ReportRideService {

    @Autowired
    ReportRideRepository reportRideRepository;

    static ReportRide unwrapReportRide(Optional<ReportRide> ReportRide, Long id) {
        if (ReportRide.isPresent()) return ReportRide.get();
        else throw new EntityNotFoundException(id, ReportRide.class);
    }
    @Override
    public ReportRide saveReportRide(ReportRide reportRide) {
        return reportRideRepository.save(reportRide);
    }

    @Override
    public ReportRide findReportRideById(Long id) {
        return unwrapReportRide(reportRideRepository.findById(id),id);
    }

    @Override
    public List<ReportRide> findAllReportRides() {
        return reportRideRepository.findAll();
    }

    @Override
    public ReportRide updateReportRide(ReportRide reportRide) {
        return reportRideRepository.save(reportRide);
    }

    @Override
    public void deleteAllReportRides() {
        reportRideRepository.deleteAll();
    }


    @Override
    public void deleteReportRideById(Long id) {
        findReportRideById(id);
        reportRideRepository.deleteById(id);
    }
}
