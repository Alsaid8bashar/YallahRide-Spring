package com.example.yallahride.ServiceTest;

import com.example.yallahride.Entity.Report;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.ReportService;
import com.example.yallahride.Service.Interface.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReportServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    ReportService reportService;
    User user;


    @BeforeAll
    public void setUp() {
        user = new User("Hasan", "ahamd", "hasan97@gmail.com", "newImage");
        userService.saveUser(user);
    }

    @Test
    public void addReport() {
        Report report = new Report("Bad driver", "very bad driver and shit car");
        report.setUser(user);
        reportService.saveReport(report);

        Assertions.assertTrue(reportService.findUserReports(user.getId()).size() > 0);
    }
}
