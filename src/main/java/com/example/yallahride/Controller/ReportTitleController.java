package com.example.yallahride.Controller;


import com.example.yallahride.Entity.Report.ReportTitle;
import com.example.yallahride.Service.Interface.ReportTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("report-title")
public class ReportTitleController {
    @Autowired
    ReportTitleService reportTitleService;

    @GetMapping("/{id}")
    public ResponseEntity<ReportTitle> getReportTitle(@PathVariable Long id) {
        return new ResponseEntity<>(reportTitleService.findReportTitleById(id), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ReportTitle> saveReportTitle(@ModelAttribute ReportTitle reportTitle) {
        return new ResponseEntity<>(reportTitleService.saveReportTitle(reportTitle), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteReportTitle(@PathVariable Long id) {
        reportTitleService.deleteReportTitleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReportTitle>> getReportTitles() {
        return new ResponseEntity<>(reportTitleService.findAllReportTitles(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus> deletePagesImages() {
        reportTitleService.deleteAllReportTitles();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("all/user-report")
    public ResponseEntity<List<ReportTitle>>getAllUserReportsTitles(){
        return new ResponseEntity<>(reportTitleService.getAllRideReportsTitles(), HttpStatus.OK);
    }

    @GetMapping("all/ride-report")
    public ResponseEntity<List<ReportTitle>>getAllRideReportsTitles(){
        return new ResponseEntity<>(reportTitleService.getAllRideReportsTitles(), HttpStatus.OK);
    }
}
