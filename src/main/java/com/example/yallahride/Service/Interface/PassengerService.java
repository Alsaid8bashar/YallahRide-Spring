package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Passenger;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;

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

    List<User> findPassengersByRideId(Long id);

    List<Passenger> findRideRequests(Long id);

    List<Ride> findUserRides(Long userId);
}
