package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Report.Report;
import com.example.yallahride.Entity.Report.ReportRide;
import com.example.yallahride.Service.Interface.ReportRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("report-ride")
public class ReportRideController {
    @Autowired
    ReportRideService reportRideService;

    @GetMapping("/{id}")
    public ResponseEntity<ReportRide> getReportRide(@PathVariable Long id) {
        return new ResponseEntity<>(reportRideService.findReportRideById(id), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ReportRide> saveReportRide(@ModelAttribute ReportRide reportRide) {
        Report report = reportRide;
        return new ResponseEntity<>(reportRideService.saveReportRide(reportRide), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteReportRide(@PathVariable Long id) {
        reportRideService.deleteReportRideById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReportRide>> getReportRides() {
        return new ResponseEntity<>(reportRideService.findAllReportRides(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus> deletePagesImages() {
        reportRideService.deleteAllReportRides();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
