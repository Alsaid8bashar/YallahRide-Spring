package com.example.yallahride.ServiceTest;

import com.example.yallahride.Entity.Rate;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.RateService;
import com.example.yallahride.Service.Interface.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RateServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    RateService rateService;
    User sender;
    User subject;


    @BeforeAll
    public void setUp() {
        sender = new User("Hasan", "ahamd","male");
        userService.saveUser(sender);

        subject = new User("Bashar", "ahamd","male");
        userService.saveUser(subject);
    }

    @Test
    @Order(1)
    public void contextLoadTest() {
        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(rateService);
    }

    @Test
    public void getRates() {
        Rate rate = new Rate(3.0);
        rate.setRater(sender);
        rate.setSubject(subject);
        rateService.saveRate(rate);

        Rate rate2 = new Rate(4.5);
        rate2.setRater(sender);
        rate2.setSubject(subject);
        rateService.saveRate(rate2);

        Assertions.assertFalse(rateService.findUserRates(subject.getId()).isEmpty());

    }

    @Test
    public void getRate() {
        Rate rate = new Rate(3.0);
        rate.setRater(sender);
        rate.setSubject(subject);
        rateService.saveRate(rate);

        Rate rate2 = new Rate(4.5);
        rate2.setRater(sender);
        rate2.setSubject(subject);
        rateService.saveRate(rate2);
        Assertions.assertTrue(rateService.getUserRateByUserId(subject.getId()) > 0);

    }


}
