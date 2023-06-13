package com.example.yallahride.ServiceTest;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.Passenger;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.CarRepository;
import com.example.yallahride.Repository.RideRepository;
import com.example.yallahride.Repository.UserRepository;
import com.example.yallahride.Service.Interface.PassengerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.Date;

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
    @Autowired
    private CarRepository carRepository;

    @BeforeAll
    public void setup() {
        user = userRepository.save(new User("Ahmad", "Mouhsn", "male"));
        Car car = carRepository.save(new Car("Black", "Ford", "Fusion", "19-89893", 2014, user));
        Date currentDate = new Date();
        LocalTime time = LocalTime.of(10, 30, 0);
        ride = rideRepository.save(new Ride("Irbid", "Amman", currentDate, 5, 2.5, user, car,time,time));
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


