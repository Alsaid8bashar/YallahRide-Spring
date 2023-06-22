package com.example.yallahride.Repository;


import com.example.yallahride.Entity.Report.ReportTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportTitleRepository extends JpaRepository<ReportTitle, Long> {

    @Query(value = "SELECT rt FROM ReportTitle rt WHERE rt.reportCategory IN (1, 2, 3, 4)")
    List<ReportTitle> getAllUserReportsTitles();

    @Query(value = "SELECT rt FROM ReportTitle rt WHERE rt.reportCategory IN (5,6,7,8)")
    List<ReportTitle> getAllRideReportsTitles();
}
