package com.example.yallahride.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "Passenger",
            joinColumns = @JoinColumn(name = "ride_fk", referencedColumnName = "ride_pk"),
            inverseJoinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "user_pk")
    )
    private Set<User> userSet;

    @OneToMany(mappedBy = "ride")
    private List<Report> reports;
}