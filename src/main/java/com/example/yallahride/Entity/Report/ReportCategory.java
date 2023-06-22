package com.example.yallahride.Entity.Report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Report_Category")
public class ReportCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_category_pk")
    private Long id;

    @Column(name = "category")
    String category;
    @Column(name = "is_user_report")
    boolean isUserReport;


}
