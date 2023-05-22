package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Passenger;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.PassengerRepository;
import com.example.yallahride.Service.Interface.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Passenger savePassenger(Passenger passenger) {
        if (passenger.getRide().isInstantBooking() && !passenger.isAccepted()) {
            passenger.setAccepted(true);
        }
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger findPassengerById(Long id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);
        return unwrapPassenger(passenger, id);
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
    public Passenger acceptPassenger(Long id) {
        Passenger passenger =unwrapPassenger(passengerRepository.findById(id), id);
        passenger.setAccepted(true);
        return passenger;
    }

    @Override
    public void rejectPassenger(Long id) {
        deletePassengerById(id);
    }

    @Override
    public List<User> findPassengersByRideId(Long id) {
        return passengerRepository.findRidePassenger(id);
    }

    @Override
    public List<Ride> findUserRides(Long userId) {
        return passengerRepository.findUserRide(userId);
    }

    private Passenger unwrapPassenger(Optional<Passenger> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        throw new EntityNotFoundException(id, Passenger.class);
    }
}
