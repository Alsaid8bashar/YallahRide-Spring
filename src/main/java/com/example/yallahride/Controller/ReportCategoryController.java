package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Report.ReportCategory;
import com.example.yallahride.Service.Interface.ReportCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("report-category")
public class ReportCategoryController {

    ReportCategoryService reportCategoryService;

    @GetMapping("/{id}")
    public ResponseEntity<ReportCategory> getReportCategory(@PathVariable Long id) {
        return new ResponseEntity<>(reportCategoryService.findReportCategoryById(id), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ReportCategory> saveReportCategory(@ModelAttribute ReportCategory ReportCategory) {
        return new ResponseEntity<>(reportCategoryService.saveReportCategory(ReportCategory), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteReportCategory(@PathVariable Long id) {
        reportCategoryService.deleteReportCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReportCategory>> getReportCategories() {
        return new ResponseEntity<>(reportCategoryService.findAllReportCategorys(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus> deleteAllReportsCategories() {
        reportCategoryService.deleteAllReportCategories();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("all/user-report")
    public ResponseEntity<List<ReportCategory>>getAllUserReportsCategories(){
        return new ResponseEntity<>(reportCategoryService.getAllUserReportsCategories(), HttpStatus.OK);
    }

    @GetMapping("all/ride-report")
    public ResponseEntity<List<ReportCategory>>getAllRideReportsCategories(){
        System.out.println(reportCategoryService.getAllRideReportsCategories());
        return new ResponseEntity<>(reportCategoryService.getAllRideReportsCategories(), HttpStatus.OK);
    }
}
