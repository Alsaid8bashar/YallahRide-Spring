package com.example.yallahride.RepostioryTest;


import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.Enum.RideStatus;
import com.example.yallahride.Entity.Passenger;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Repository.CarRepository;
import com.example.yallahride.Repository.PassengerRepository;
import com.example.yallahride.Repository.RideRepository;
import com.example.yallahride.Repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalTime;
import java.util.Date;
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
    @Autowired
    CarRepository carRepository;

    User user;
    Ride ride;
    Passenger passenger;
    Car car;


    @BeforeAll
    public void setup() {
        user = userRepository.save(new User("Ahmad", "Mouhsn", "male"));
        car = carRepository.save(new Car("Black", "Ford", "Fusion", "19-89893", 2014, user));
        Date currentDate = new Date();
        LocalTime time = LocalTime.of(10, 30, 0);
        //TODO
        ride = rideRepository.save(new Ride("Irbid", "Amman", currentDate, 5, 2.5, user, car, time, time,currentDate));
        passenger = new Passenger(user, ride, RideStatus.COMPLETED);
        passenger.setAccepted(true);
        passenger = passengerRepository.save(passenger);
    }

    @Test
    @Order(1)
    public void addPassenger() {
        Passenger passenger = new Passenger(user, ride, RideStatus.COMPLETED);
        passenger.setAccepted(true);
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
//        List<User> users = passengerRepository.findRidePassenger(ride.getId());
//        Assertions.assertTrue(users.contains(user));
    }

    @Test
    @Order(5)
    public void testFindUserRide() {
        List<Passenger> rides = passengerRepository.findUserRide(user.getId());
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
