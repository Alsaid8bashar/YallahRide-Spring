package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Report;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.RideRepository;
import com.example.yallahride.Service.Interface.RideService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RideServiceImpl implements RideService {

    final private RideRepository rideRepository;

    public RideServiceImpl(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    @Override
    public Ride saveRide(Ride ride) {
        return rideRepository.save(ride);
    }

    @Override
    public Ride findRideById(Long id) {
        return unwrapRide(rideRepository.findById(id),id);
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
    public Set<Report> findRideReports(Ride ride) {
        return ride.getReports();
    }

    static Ride unwrapRide(Optional<Ride> ride, Long id) {
        if (ride.isPresent()) return ride.get();
        else throw new EntityNotFoundException(id, Ride.class);
    }
}
