package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Report;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.RideRepository;
import com.example.yallahride.Service.Interface.FileService;
import com.example.yallahride.Service.Interface.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RideServiceImpl implements RideService {

    final private RideRepository rideRepository;

    @Autowired
    private FileService fileService;


    public RideServiceImpl(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    private Ride unwrapRide(Optional<Ride> ride, Long id) {
        if (ride.isPresent()) return ride.get();
        else throw new EntityNotFoundException(id, Ride.class);
    }

    @Override
    public Ride saveRide(Ride ride) {
        return rideRepository.save(ride);
    }

    @Override
    public Ride findRideById(Long id) {
        Ride ride = unwrapRide(rideRepository.findById(id), id);
        return ride;
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

    @Override
    public Collection<Ride> searchRidesByFromAndToAndDate(String from, String to, Date date) {
        return rideRepository.searchRidesByFromAndToAndDate(from, to, date);
    }

    public Collection<Ride> findDriverRides(long driverId) {
        return rideRepository.findDriverRide(driverId);
    }
}
