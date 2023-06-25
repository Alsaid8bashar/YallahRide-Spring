package com.example.yallahride.Entity.Report;

import com.example.yallahride.Entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "report_pk")
    protected int reportPk;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "reporter_fk", referencedColumnName = "user_pk")
    protected User report;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "title_fk", referencedColumnName = "report_title_pk")
    protected ReportTitle title;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_fk", referencedColumnName = "report_category_pk")
    protected ReportCategory category;

    @Column(name = "description", nullable = false)
    protected String description;

    @Column(name = "is_solved")
    protected boolean isSolved;

    @Column(name = "date", nullable = false)
    protected Date date;


}
