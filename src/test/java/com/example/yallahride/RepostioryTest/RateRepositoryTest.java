package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.Rate;
import com.example.yallahride.Repository.RateRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RateRepositoryTest {
    @Autowired
    RateRepository rateRepository;
    Rate report;

    @BeforeAll
    public void setup() {
        report = rateRepository.save(new Rate(1));
    }

    @Test
    @Order(1)
    public void testCreateRide() {
        Rate report = rateRepository.save(new Rate(2));
        Assertions.assertTrue(report.getId() > 0);
    }


    @Test
    @Order(2)
    public void testFindRideById() {
        Optional<Rate> optionalRide = rateRepository.findById(report.getId());
        Rate tempRide = optionalRide.get();
        Assertions.assertEquals(tempRide.getId(), report.getId());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testUpdateRide() {
        Optional<Rate> optionalRide = rateRepository.findById(report.getId());
        report.setRate(4);
        Rate rideUpdated = rateRepository.save(report);
        Assertions.assertEquals(report.getRate(), rideUpdated.getRate());
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void testFindAllRides() {
        List<Rate> rideList = rateRepository.findAll();
        Assertions.assertTrue(rideList.size() > 0);
    }

    @Test
    @Order(5)
    public void testDeleteRideByID() {
        rateRepository.deleteById(report.getId());
        Optional<Rate> optionalRide = rateRepository.findById(report.getId());
        Assertions.assertTrue(!optionalRide.isPresent());
    }

    @Test
    @Order(6)
    public void testDeleteAllRides() {
        rateRepository.deleteAll();
        Assertions.assertTrue(rateRepository.count() == 0);
    }
}
