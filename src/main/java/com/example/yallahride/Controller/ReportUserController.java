package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Report.Report;
import com.example.yallahride.Entity.Report.ReportUser;
import com.example.yallahride.Service.Interface.ReportUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("report-user")
public class ReportUserController {
    @Autowired
    ReportUserService reportUserService;

    @GetMapping("/{id}")
    public ResponseEntity<ReportUser> getReportUser(@PathVariable Long id) {
        return new ResponseEntity<>(reportUserService.findReportUserById(id), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ReportUser> saveReportUser(@ModelAttribute ReportUser reportUser) {
        return new ResponseEntity<>(reportUserService.saveReportUser(reportUser), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteReportUser(@PathVariable Long id) {
        reportUserService.deleteReportUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReportUser>> getReportUsers() {
        return new ResponseEntity<>(reportUserService.findAllReportUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus> deletePagesImages() {
        reportUserService.deleteAllReportUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
