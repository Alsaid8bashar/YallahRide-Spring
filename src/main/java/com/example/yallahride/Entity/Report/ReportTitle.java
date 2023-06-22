package com.example.yallahride.Entity.Report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Report_Title")
public class ReportTitle
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_title_pk")
    private Long id;
    @Column(name = "title")
    String title;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_fk", referencedColumnName = "report_category_pk")
    ReportCategory reportCategory;
}
