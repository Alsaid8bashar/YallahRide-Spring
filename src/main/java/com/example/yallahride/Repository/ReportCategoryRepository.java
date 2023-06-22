package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Report.ReportCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportCategoryRepository extends JpaRepository<ReportCategory, Long> {

    @Query(value = "SELECT rc FROM ReportCategory rc WHERE rc.isUserReport = 1")
    List<ReportCategory> getAllUserReportsCategories();

    @Query(value = "SELECT rc FROM ReportCategory rc WHERE rc.isUserReport = 0")
    List<ReportCategory> getAllRideReportsCategories();
}
