package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Report;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Repository.ReportRepository;
import com.example.yallahride.Repository.RideRepository;
import com.example.yallahride.Service.Interface.RideService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RideServiceImpl implements RideService {
    final private ReportRepository reportRepository;
    final private RideRepository rideRepository;

    public RideServiceImpl(RideRepository rideRepository, ReportRepository reportRepository) {
        this.rideRepository = rideRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public Ride saveRide(Ride ride) {
        return rideRepository.save(ride);
    }

    @Override
    public Optional<Ride> findRideById(Long id) {
        return rideRepository.findById(id);
    }

    @Override
    public List<Ride> findAllRides() {
        return rideRepository.findAll();
    }

    @Override
    public Ride updateRide(Ride ride) {
        return rideRepository.save(ride);
    }

    @Override
    public void deleteAllRides() {
        rideRepository.deleteAll();
    }

    @Override
    public void deleteRideById(Long id) {
        rideRepository.deleteById(id);
    }

    @Override
    public long getNumberOfRide() {
        return rideRepository.count();
    }

    @Override
    public List<Report> findRideReports(Ride ride) {
        List<Long> reportIds = new ArrayList<Long>();
        for (Report element : ride.getReports()) {
            reportIds.add(element.getId());
        }
        return reportRepository.findAllById(reportIds);
    }

    @Override
    public List<Ride> searchRidesByCriteria(String fromLocation, String toLocation,
                                            LocalDate date, int numSeats) {
        return rideRepository.searchRidesByCriteria(fromLocation, toLocation, date, numSeats);
    }
}
