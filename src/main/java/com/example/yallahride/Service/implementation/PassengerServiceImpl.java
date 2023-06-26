package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Enum.RideStatus;
import com.example.yallahride.Entity.Passenger;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.PassengerRepository;
import com.example.yallahride.Service.Interface.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
    public void acceptPassenger(Long id) {
        passengerRepository.acceptPassenger(id);
    }

    @Override
    public void rejectPassenger(Long id) {
//        passengerRepository.acceptPassenger(id);

    }

    @Override
    public void changeBookingStatus(Long userId, Long rideId, RideStatus rideStatus) {
        passengerRepository.cancelBookingByUserId(userId, rideId, rideStatus);
    }

    @Override
    public List<Passenger> findPassengersByRideId(Long id) {
        List<Passenger> passengers = passengerRepository.findByRide_Id(id);
        return passengers;
    }

    @Override
    public List<Passenger> findRideRequests(Long id) {
        List<Passenger> passengers = passengerRepository.findByRide_IdAndIsAcceptedFalse(id);
        return passengers;
    }


    @Override
    public List<Passenger> findUserRides(Long userId) {
        List<Passenger> passengers = passengerRepository.findByUser_IdAndIsAcceptedTrue(userId);

        return passengers;
    }

    private Passenger unwrapPassenger(Optional<Passenger> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        throw new EntityNotFoundException(id, Passenger.class);
    }
}
