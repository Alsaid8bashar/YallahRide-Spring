package com.example.yallahride.Controller;

import com.amazonaws.services.xray.model.Http;
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

    @PutMapping("/update")
    public ResponseEntity<Passenger> updatePassenger(@RequestBody Passenger Passenger) {
        return new ResponseEntity<>(passengerService.savePassenger(Passenger), OK);
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<Http> acceptPassenger(@PathVariable long id) {
        passengerService.acceptPassenger(id);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<HttpStatus> rejectPassenger(@PathVariable long id) {
        passengerService.rejectPassenger(id);
        return new ResponseEntity<>(OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassengerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus> deleteAllPassenger() {
        passengerService.deleteAllPassengers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Passenger>> getPassengers() {
        return new ResponseEntity<>(passengerService.findAllPassengers(), HttpStatus.OK);
    }

    @GetMapping("/user-rides/{userId}")
    public ResponseEntity<List<Ride>> getUserRides(@PathVariable long userId) {
        return new ResponseEntity<>(passengerService.findUserRides(userId), HttpStatus.OK);
    }

    @GetMapping("/ride-passengers/{rideId}")
    public ResponseEntity<List<User>> getRidePassengers(@PathVariable long rideId) {
        return new ResponseEntity<>(passengerService.findPassengersByRideId(rideId), HttpStatus.OK);
    }

    @GetMapping("/ride-requests/{rideId}")
    public ResponseEntity<List<Passenger>> findRideRequests(@PathVariable long rideId) {
        return new ResponseEntity<>(passengerService.findRideRequests(rideId), HttpStatus.OK);
    }


}
