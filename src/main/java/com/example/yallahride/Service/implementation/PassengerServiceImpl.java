package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Passenger;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Repository.PassengerRepository;
import com.example.yallahride.Service.Interface.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger findPassengerById(Long id) {
        return passengerRepository.findById(id).get();
    }

    @Override
    public List<Passenger> findAllPassengers() {
        return passengerRepository.findAll();
    }


    @Override
    public void deleteAllPassengers() {
        passengerRepository.deleteAll();
    }

    @Override
    public void deletePassengerById(Long id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public long getNumberOfPassenger() {
        return passengerRepository.count();
    }

    @Override
    public List<User> findPassengersByRideId(Long id) {
        return passengerRepository.findRidePassenger(id);
    }

    @Override
    public List<Ride> findUserRides(Long userId) {
        return passengerRepository.findUserRide(userId);
    }
}
