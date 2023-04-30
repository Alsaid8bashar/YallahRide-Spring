package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Ride;

import java.util.List;
import java.util.Optional;

public interface RideService {

    void saveRide(Ride ride);

    Optional<Ride> findRideById(Long id);

    List<Ride> findAllRides();

    void deleteAllRides();

    void deleteRideById(Long id);

    long getNumberOfRide();
}
