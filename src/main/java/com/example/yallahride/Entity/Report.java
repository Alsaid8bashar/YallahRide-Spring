package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @Column(name = "report_pk")
    private Long id;
    @NonNull
    private String title, description;
    @CreationTimestamp
    private Date date;
    @ManyToOne
    @JoinColumn(name = "ride_fk", referencedColumnName = "ride_pk")
    @ToString.Exclude
    private Ride ride;

    @ManyToOne
    @JoinColumn(name = "user_fk", referencedColumnName = "user_pk")
    @ToString.Exclude
    private User user;
}
