package com.example.yallahride.ServiceTest;

import com.example.yallahride.Entity.Passenger;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.RideRepository;
import com.example.yallahride.Repository.UserRepository;
import com.example.yallahride.Service.Interface.PassengerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PassengerServiceTest {


    @Autowired
    PassengerService passengerService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RideRepository rideRepository;
    User user;
    Ride ride;
    Passenger passenger;

    @BeforeAll
    public void setup() {
        user = userRepository.save(new User("Ahmad", "Mouhsn", "ahmadmouhsn@gmail.com"));
        ride = rideRepository.save(new Ride("Irbid", "Amman", 3, 2.5, user));
        passenger = passengerService.savePassenger(new Passenger(user, ride));
    }

    @Test
    @Order(1)
    public void acceptPassenger() {
        Passenger tempPassenger = passengerService.acceptPassenger(passenger.getId());
        Assertions.assertTrue(tempPassenger.isAccepted());
    }

    @Test
    @Order(2)
    public void rejectPassenger() {
        passengerService.rejectPassenger(passenger.getId());
        Assertions.assertThrowsExactly(EntityNotFoundException.class, () ->
                passengerService.findPassengerById(user.getId()));
    }
}


