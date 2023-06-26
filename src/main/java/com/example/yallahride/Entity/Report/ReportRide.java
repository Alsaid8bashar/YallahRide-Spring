package com.example.yallahride.Entity.Report;

import com.example.yallahride.Entity.Ride;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Report_Ride")
public class ReportRide extends Report {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "ride_fk", referencedColumnName = "ride_pk")
    private Ride ride;

}
