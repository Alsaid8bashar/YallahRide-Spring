//package com.example.yallahride.ServiceTest;
//
//import com.example.yallahride.Entity.Report;
//import com.example.yallahride.Entity.User;
//import com.example.yallahride.Service.Interface.ReportService;
//import com.example.yallahride.Service.Interface.UserService;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class ReportServiceTest {
//
//    @Autowired
//    UserService userService;
//    @Autowired
//    ReportService reportService;
//    User user;
//    Report report;
//
//
//    @BeforeAll
//    public void setUp() {
//        user = userService.saveUser(new User("Hasan", "ahamd","male"));
//        report = reportService.saveReport(new Report("Bad driver", "very bad driver and shit car"));
//    }
//    @Test
//    @Order(1)
//    public void contextLoadTest() {
//        Assertions.assertNotNull(userService);
//        Assertions.assertNotNull(reportService);
//    }
//
//    @Test
//    @Order(1)
//    public void addReport() {
//        report.setUser(user);
//        report = reportService.saveReport(report);
//        Assertions.assertTrue(reportService.findUserReports(user.getId()).size() > 0);
//    }
//
//    @Test
//    @Order(2)
//    public void should_not_remove_child_when_parent_removed(){
//        reportService.deleteReportById(report.getId());
//        Assertions.assertTrue(userService.findUserById(user.getId()).getId() > 0);
//    }
//}
