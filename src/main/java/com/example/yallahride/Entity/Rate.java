package com.example.yallahride.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name = "date")
    private Date date;
    @NonNull
    private Double rate;

    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinColumn(name = "rater_fk")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    private User rater;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    @JoinColumn(name = "subject_fk")
    private User subject;
}
