package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Passenger;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
@RestController
@RequestMapping("passenger")

public class PassengerController {
    @Autowired
    private PassengerService passengerService;


    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable Long id) {
        return new ResponseEntity<>(passengerService.findPassengerById(id), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Passenger> savePassenger(@RequestBody Passenger Passenger) {
        return new ResponseEntity<>(passengerService.savePassenger(Passenger), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassengerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Passenger>> getPassengers() {
        return new ResponseEntity<>(passengerService.findAllPassengers(), HttpStatus.OK);
    }

    @GetMapping("/user-rides")
    public ResponseEntity<List<Ride>> getUserRides(@RequestParam Long userId) {
        return new ResponseEntity<>(passengerService.findUserRides(userId), HttpStatus.OK);
    }

    @GetMapping("/ride-passengers")
    public ResponseEntity<List<User>> getRidePassengers(@RequestParam Long rideId) {
        return new ResponseEntity<>(passengerService.findPassengersByRideId(rideId), HttpStatus.OK);
    }

}
