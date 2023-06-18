package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Enum.RideStatus;
import com.example.yallahride.Entity.Passenger;

import java.util.List;


public interface PassengerService {
    Passenger savePassenger(Passenger passenger);

    Passenger findPassengerById(Long id);

    List<Passenger> findAllPassengers();

    void deleteAllPassengers();

    void deletePassengerById(Long id);

    long getNumberOfPassenger();

    void acceptPassenger(Long id);

    void rejectPassenger(Long id);
    void changeBookingStatus(long userId, RideStatus rideStatus);


    List<Passenger> findPassengersByRideId(Long id);

    List<Passenger> findRideRequests(Long id);

    List<Passenger> findUserRides(Long userId);
}
