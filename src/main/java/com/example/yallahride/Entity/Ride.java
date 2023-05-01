package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Ride")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_pk", nullable = false)
    private Long ridePk;
    @NonNull
    private String from, to;

    @CreationTimestamp
    private Date date;
    @NonNull
    private int seats;

    @OneToMany(mappedBy = "ride")
    private java.util.List<Report> reports;
}