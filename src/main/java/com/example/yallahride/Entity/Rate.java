package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_pk")
    private Long id;
    @CreationTimestamp
    private Date date;
    @NonNull
    private int rate;
    @ManyToOne
    @JoinColumn(name="rater_fk")
    private User rater;

    @ManyToOne
    @JoinColumn(name="subject_fk")
    private User subject ;
}
