package com.example.yallahride.Entity.Report;

import com.example.yallahride.Entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Report_User")
public class ReportUser extends Report {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_fk", referencedColumnName = "user_pk")
    private User subject;
}
