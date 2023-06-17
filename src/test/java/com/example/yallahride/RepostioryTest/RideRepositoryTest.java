package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Repository.CarRepository;
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
public class RideRepositoryTest {

    @Autowired
    RideRepository rideRepository;

    @Autowired
    UserRepository userRepository;
    Ride ride;
    @Autowired
    private CarRepository carRepository;
    private Car car;
    LocalTime time = LocalTime.of(10, 30, 0);


    @BeforeAll
    public void setup() {
        User user = userRepository.save(new User("Ahmad", "Mouhsn", "male"));
        car = carRepository.save(new Car("Black", "Ford", "Fusion", "19-89893", 2014, user));
        Date currentDate = new Date();
        ride = rideRepository.save(new Ride("Irbid", "Amman", currentDate, 5,2.5, user,car,time,time,currentDate));
    }

    @Test
    @Order(1)
    public void testCreateRide() {
        User user = userRepository.save(new User("Ahmad", "Mouhsn", "male"));
        Date currentDate = new Date();

        Ride ride = rideRepository.save(new Ride("Irbid", "Amman", currentDate, 5,2.5, user,car,time,time,currentDate));
        Assertions.assertTrue(ride.getId() > 0);
    }


    @Test
    @Order(2)
    public void testFindRideById() {
        Optional<Ride> optionalRide = rideRepository.findById(ride.getId());
        Ride tempRide = optionalRide.get();
        Assertions.assertEquals(tempRide.getId(), ride.getId());
    }

    @Test
    @Order(2)
    public void testSearchForRide() {
        User user = userRepository.save(new User("Ahmad", "Mouhsn", "male"));
        Date currentDate = new Date();
        Ride ride = rideRepository.save(new Ride("Irbid", "Amman", currentDate, 5,2.5, user,car,time,time,currentDate));
        List<Ride> tempRide = (List<Ride>) rideRepository.searchRidesByFromAndToAndDate(ride.getFrom(), ride.getTo(), ride.getDepartureDate());
        Assertions.assertTrue(tempRide.contains(ride));
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testUpdateRide() {
        Optional<Ride> optionalRide = rideRepository.findById(ride.getId());
        ride.setFrom("Aqaba");
        Ride rideUpdated = rideRepository.save(ride);
        Assertions.assertEquals(ride.getFrom(), rideUpdated.getFrom());
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void testFindAllRides() {
        List<Ride> rideList = rideRepository.findAll();
        Assertions.assertTrue(rideList.size() > 0);
    }

    @Test
    @Order(5)
    public void testDeleteRideByID() {
        rideRepository.deleteById(ride.getId());
        Optional<Ride> optionalRide = rideRepository.findById(ride.getId());
        Assertions.assertTrue(!optionalRide.isPresent());
    }

    @Test
    @Order(6)
    public void testDeleteAllRides() {
        rideRepository.deleteAll();
        Assertions.assertTrue(rideRepository.count() == 0);
    }
}
