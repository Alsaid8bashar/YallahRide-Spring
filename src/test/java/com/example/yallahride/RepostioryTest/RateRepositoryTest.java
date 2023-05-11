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
    Rate rate;

    @BeforeAll
    public void setup() {
        rate = rateRepository.save(new Rate(1.0));
    }

    @Test
    @Order(1)
    public void testCreateRate() {
        Rate report = rateRepository.save(new Rate(2.0));
        Assertions.assertTrue(report.getId() > 0);
    }


    @Test
    @Order(2)
    public void testFindRateById() {
        Optional<Rate> optionalRide = rateRepository.findById(rate.getId());
        Rate tempRide = optionalRide.get();
        Assertions.assertEquals(tempRide.getId(), rate.getId());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testUpdateRate() {
        Optional<Rate> optionalRide = rateRepository.findById(rate.getId());
        rate.setRate(4.0);
        Rate rideUpdated = rateRepository.save(rate);
        Assertions.assertEquals(rate.getRate(), rideUpdated.getRate());
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void testFindAllRates() {
        List<Rate> rideList = rateRepository.findAll();
        Assertions.assertTrue(rideList.size() > 0);
    }

    @Test
    @Order(5)
    public void testDeleteRateByID() {
        rateRepository.deleteById(rate.getId());
        Optional<Rate> optionalRide = rateRepository.findById(rate.getId());
        Assertions.assertTrue(!optionalRide.isPresent());
    }

//    @Test
//    @Order(5)
//    public void testDeleteRate() {
//        rateRepository.delete(rate);
//        Optional<Rate> optionalRide = rateRepository.findById(rate.getId());
//        Assertions.assertTrue(!optionalRide.isPresent());
//    }


    @Test
    @Order(6)
    public void testDeleteAllRates() {
        rateRepository.deleteAll();
        Assertions.assertTrue(rateRepository.count() == 0);
    }
}
