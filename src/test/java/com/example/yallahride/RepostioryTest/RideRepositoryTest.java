package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;
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
public class RideRepositoryTest {

    @Autowired
    RideRepository rideRepository;

    @Autowired
    UserRepository userRepository;
    Ride ride;

    @BeforeAll
    public void setup() {
        User   user = userRepository.save(new User("Ahmad", "Mouhsn", "ahmadmouhsn@gmail.com"));
        ride = rideRepository.save(new Ride("Irbid", "Amman", 3,user));
    }

    @Test
    @Order(1)
    public void testCreateRide() {
        User   user = userRepository.save(new User("Ahmad", "Mouhsn", "ahmadmouhsn@gmail.com"));

        Ride ride = rideRepository.save(new Ride("Jarash", "Zarqa", 3,user));
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
