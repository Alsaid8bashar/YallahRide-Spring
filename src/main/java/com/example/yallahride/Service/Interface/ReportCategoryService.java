package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Report.ReportCategory;

import java.util.List;

public interface ReportCategoryService {
    ReportCategory saveReportCategory(ReportCategory ReportCategory);

    ReportCategory findReportCategoryById(Long id);

    List<ReportCategory> findAllReportCategorys();

    ReportCategory updateReportCategory(ReportCategory ReportCategory);

    void deleteAllReportCategories();

    void deleteReportCategoryById(Long id);

    List<ReportCategory> getAllUserReportsCategories();

    List<ReportCategory> getAllRideReportsCategories();
}
