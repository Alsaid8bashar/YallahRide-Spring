package com.example.yallahride.Service.implementation;

import com.example.yallahride.Dto.AdminDTO;
import com.example.yallahride.Entity.Account;
import com.example.yallahride.Entity.Report.ReportRide;
import com.example.yallahride.Entity.Report.ReportUser;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Service.Interface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDtoServiceImpl implements AdminDtoService {

    @Autowired
    AccountService accountService;
    @Autowired
    RideService rideService;
    @Autowired
    ReportUserService reportUserService;
    @Autowired
    ReportRideService reportRideService;

    @Override
    public AdminDTO getAdminDto() {
        AdminDTO adminDTO = AdminDTO.builder()
                .accounts(accountService.findAllAccounts())
                .rides(rideService.findAllRides())
                .reportUsers(reportUserService.findAllReportUsers())
                .reportRides(reportRideService.findAllReportRides())
                .build();
        return adminDTO;
    }
}
