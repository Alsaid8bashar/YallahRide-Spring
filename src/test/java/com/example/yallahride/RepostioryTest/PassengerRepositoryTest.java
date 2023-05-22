package com.example.yallahride.RepostioryTest;


import com.example.yallahride.Entity.Passenger;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Repository.PassengerRepository;
import com.example.yallahride.Repository.RideRepository;
import com.example.yallahride.Repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PassengerRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    RideRepository rideRepository;
    User user;
    Ride ride;
    Passenger passenger;

    @BeforeAll
    public void setup() {
        user = userRepository.save(new User("Ahmad", "Mouhsn", "ahmadmouhsn@gmail.com","male"));
        ride = rideRepository.save(new Ride("Irbid", "Amman", 3, 2.5, user));
        passenger = passengerRepository.save(new Passenger(user, ride));
    }

    @Test
    @Order(1)
    public void addPassenger() {
        Passenger passenger = new Passenger(user, ride);
        passengerRepository.save(passenger);
        Assertions.assertTrue(passengerRepository.findById(passenger.getId()).isPresent());
    }

    @Test
    @Order(2)
    public void testFindPassengerById() {
        Optional<Passenger> optionalPassenger = passengerRepository.findById(passenger.getId());
        Passenger tempPassenger = optionalPassenger.get();
        Assertions.assertEquals(tempPassenger.getId(), passenger.getId());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testFindAllPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        Assertions.assertTrue(passengers.size() > 0);
    }

    @Test
    @Order(4)
    public void testFindRidePassenger() {
        List<User> users = passengerRepository.findRidePassenger(ride.getId());
        Assertions.assertTrue(users.contains(user));
    }

    @Test
    @Order(5)
    public void testFindUserRide() {
        List<Ride> rides = passengerRepository.findUserRide(user.getId());
        Assertions.assertTrue(rides.contains(ride));
    }

    @Test
    @Order(6)
    public void testDeletePassengerByID() {
        passengerRepository.deleteById(passenger.getId());
        Optional<Passenger> optionalPassenger = passengerRepository.findById(passenger.getId());
        Assertions.assertFalse(optionalPassenger.isPresent());
    }


    @Test
    @Order(7)
    public void testDeleteAllPassenger() {
        passengerRepository.deleteAll();
        Assertions.assertTrue(passengerRepository.findAll().isEmpty());
    }

}
