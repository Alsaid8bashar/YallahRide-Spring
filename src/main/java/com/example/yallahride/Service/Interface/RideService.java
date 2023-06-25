package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Enum.RideStatus;
import com.example.yallahride.Entity.Ride;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface RideService {

    Ride saveRide(Ride ride);

    Ride findRideById(Long id);

    List<Ride> findAllRides();

    Ride updateRide(Ride ride);

    void deleteAllRides();

    void deleteRideById(Long id);

    long getNumberOfRide();

//    Set<Report> findRideReports(Ride ride);

    Collection<Ride> searchRidesByFromAndToAndDate(String from, String to, Date date);

    Collection<Ride> findDriverRides(long driverId);

    void changeRideStatus( long rideId, RideStatus rideStatus);
}
