package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Report.ReportUser;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.ReportUserRepository;
import com.example.yallahride.Service.Interface.ReportUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportUserServiceImpl implements ReportUserService {

    @Autowired
    ReportUserRepository reportUserRepository;

    static ReportUser unwrapReportUser(Optional<ReportUser> ReportUser, Long id) {
        if (ReportUser.isPresent()) return ReportUser.get();
        else throw new EntityNotFoundException(id, ReportUser.class);
    }
    @Override
    public ReportUser saveReportUser(ReportUser ReportUser) {
        return reportUserRepository.save(ReportUser);
    }

    @Override
    public ReportUser findReportUserById(Long id) {
        return unwrapReportUser(reportUserRepository.findById(id),id);
    }

    @Override
    public List<ReportUser> findAllReportUsers() {
        return reportUserRepository.findAll();
    }

    @Override
    public ReportUser updateReportUser(ReportUser ReportUser) {
        return reportUserRepository.save(ReportUser);
    }

    @Override
    public void deleteAllReportUsers() {
        reportUserRepository.deleteAll();
    }


    @Override
    public void deleteReportUserById(Long id) {
        findReportUserById(id);
        reportUserRepository.deleteById(id);
    }
}
