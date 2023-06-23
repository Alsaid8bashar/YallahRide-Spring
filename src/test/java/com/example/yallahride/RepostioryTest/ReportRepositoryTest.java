//package com.example.yallahride.RepostioryTest;
//
//import com.example.yallahride.Entity.Report;
//import com.example.yallahride.Repository.ReportRepository;
//import org.junit.Before;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.List;
//import java.util.Optional;
//
//@DataJpaTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class ReportRepositoryTest {
//
//    @Autowired
//    ReportRepository reportRepository;
//    Report report;
//
//    @BeforeAll
//    public void setup() {
//        report = reportRepository.save(new Report("Driver was rude!!", "A really rude driver"));
//    }
//
//    @Test
//    @Order(1)
//    public void testCreateRide() {
//        Report report = reportRepository.save(new Report("Driver was driving crazy!", "Driver did not obey to the road rules!"));
//        Assertions.assertTrue(report.getId() > 0);
//    }
//
//
//    @Test
//    @Order(2)
//    public void testFindRideById() {
//        Optional<Report> optionalRide = reportRepository.findById(report.getId());
//        Report tempRide = optionalRide.get();
//        Assertions.assertEquals(tempRide.getId(), report.getId());
//    }
//
//    @Test
//    @Order(3)
//    @Rollback(value = false)
//    public void testUpdateRide() {
//        Optional<Report> optionalRide = reportRepository.findById(report.getId());
//        report.setTitle("Updated title");
//        Report rideUpdated = reportRepository.save(report);
//        Assertions.assertEquals(report.getTitle(), rideUpdated.getTitle());
//    }
//
//    @Test
//    @Order(4)
//    @Rollback(value = false)
//    public void testFindAllRides() {
//        List<Report> rideList = reportRepository.findAll();
//        Assertions.assertTrue(rideList.size() > 0);
//    }
//
//    @Test
//    @Order(5)
//    public void testDeleteRideByID() {
//        reportRepository.deleteById(report.getId());
//        Optional<Report> optionalRide = reportRepository.findById(report.getId());
//        Assertions.assertTrue(!optionalRide.isPresent());
//    }
//
//    @Test
//    @Order(6)
//    public void testDeleteAllRides() {
//        reportRepository.deleteAll();
//        Assertions.assertTrue(reportRepository.count() == 0);
//    }
//}
