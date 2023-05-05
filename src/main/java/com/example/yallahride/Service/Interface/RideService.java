package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Report;
import com.example.yallahride.Entity.Ride;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RideService {

    Ride saveRide(Ride ride);

    Optional<Ride> findRideById(Long id);

    List<Ride> findAllRides();

    Ride updateRide(Ride ride);

    void deleteAllRides();

    void deleteRideById(Long id);

    long getNumberOfRide();

    Set<Report> findRideReports(Ride ride);
}
