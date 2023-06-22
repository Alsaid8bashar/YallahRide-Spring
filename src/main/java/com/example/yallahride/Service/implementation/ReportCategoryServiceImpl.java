package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Report.ReportCategory;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.ReportCategoryRepository;
import com.example.yallahride.Service.Interface.ReportCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportCategoryServiceImpl implements ReportCategoryService {

    @Autowired
    ReportCategoryRepository reportCategoryRepository;

    static ReportCategory unwrapReportCategory(Optional<ReportCategory> reportCategory, Long id) {
        if (reportCategory.isPresent()) return reportCategory.get();
        else throw new EntityNotFoundException(id, ReportCategory.class);
    }
    @Override
    public ReportCategory saveReportCategory(ReportCategory reportCategory) {
        return reportCategoryRepository.save(reportCategory);
    }

    @Override
    public ReportCategory findReportCategoryById(Long id) {
        return unwrapReportCategory(reportCategoryRepository.findById(id),id);
    }

    @Override
    public List<ReportCategory> findAllReportCategorys() {
        return reportCategoryRepository.findAll();
    }

    @Override
    public ReportCategory updateReportCategory(ReportCategory reportCategory) {
        return reportCategoryRepository.save(reportCategory);
    }

    @Override
    public void deleteAllReportCategories() {
        reportCategoryRepository.deleteAll();
    }

    @Override
    public void deleteReportCategoryById(Long id) {
        findReportCategoryById(id);
        reportCategoryRepository.deleteById(id);
    }

    @Override
    public List<ReportCategory> getAllUserReportsCategories() {
        return reportCategoryRepository.getAllUserReportsCategories();
    }

    @Override
    public List<ReportCategory> getAllRideReportsCategories() {
        return reportCategoryRepository.getAllRideReportsCategories();
    }
}
