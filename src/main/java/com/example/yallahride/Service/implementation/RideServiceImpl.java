package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Repository.RideRepository;
import com.example.yallahride.Service.Interface.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RideServiceImpl implements RideService {
    @Autowired
    RideRepository rideRepository;
    @Override
    public void saveRide(Ride ride) {
        rideRepository.save(ride);
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
}
