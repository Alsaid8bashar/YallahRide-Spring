package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_pk", nullable = false)
    private Long reportPk;
    private String title, description;
    private Date data;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ride_fk", referencedColumnName = "ride_pk")
    private Ride ride;
}
