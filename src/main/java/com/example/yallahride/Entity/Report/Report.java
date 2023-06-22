package com.example.yallahride.Entity.Report;

import com.example.yallahride.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "report_pk")
    protected int reportPk;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "reporter_fk", referencedColumnName = "user_pk")
    @JsonIgnore
    protected User report;

    @Column(name = "title_fk")
    protected int titleFk;

    @Column(name = "category_fk")
    protected int categoryFk;

    @Column(name = "description", nullable = false)
    protected String description;

    @Column(name = "is_solved")
    protected boolean isSolved;

    @Column(name = "date", nullable = false)
    protected Date date;

    public int getReportPk() {
        return reportPk;
    }

    public void setReportPk(int reportPk) {
        this.reportPk = reportPk;
    }

    public User getReport() {
        return report;
    }

    public void setReport(User report) {
        this.report = report;
    }

    public int getTitleFk() {
        return titleFk;
    }

    public void setTitleFk(int titleFk) {
        this.titleFk = titleFk;
    }

    public int getCategoryFk() {
        return categoryFk;
    }

    public void setCategoryFk(int categoryFk) {
        this.categoryFk = categoryFk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
