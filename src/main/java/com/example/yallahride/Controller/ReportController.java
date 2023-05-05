package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Report;
import com.example.yallahride.Service.Interface.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    ReportService reportService;


    @PostMapping("/create")
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        return new ResponseEntity<>(reportService.saveReport(report), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Report> updateReport(@RequestBody Report report) {
        return new ResponseEntity<>(reportService.saveReport(report), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Report>> findReportById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(reportService.findReportById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Report>> findAllReports() {
        return new ResponseEntity<>(reportService.findAllReports(), HttpStatus.OK);
    }

    @GetMapping("/user/reports")
    public ResponseEntity<Collection<Report>> findUserReports(@RequestBody Long id) {
        return new ResponseEntity<>(reportService.findUserReports(id), HttpStatus.OK);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteReportById(@PathVariable("id") Long id) {
        reportService.deleteReportById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus> deleteAllReports() {
        reportService.deleteAllReports();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Long> getReportsCount() {
        return new ResponseEntity<>(reportService.getNumberOfReport(), HttpStatus.OK);
    }
}
