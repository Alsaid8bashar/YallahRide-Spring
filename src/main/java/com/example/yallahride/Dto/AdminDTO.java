package com.example.yallahride.Dto;

import com.example.yallahride.Entity.Account;
import com.example.yallahride.Entity.Report.ReportRide;
import com.example.yallahride.Entity.Report.ReportUser;
import com.example.yallahride.Entity.Ride;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private List<Account> accounts;
    private List<Ride> rides;
    private List<ReportUser> reportUsers;
    private List<ReportRide> reportRides;
}
