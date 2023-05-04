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
    @Column(name = "ride_pk")
    private Long id;
    @NonNull
    @Column(name = "`from`")
    private String from;
    @NonNull
    @Column(name = "`to`")
    private String to;

    @Column(name = "`date`")
    private Date date;
    @NonNull
    @Column(name = "seats")
    private int seats;
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "Passenger",
            joinColumns = @JoinColumn(name = "ride_fk", referencedColumnName = "ride_pk"),
            inverseJoinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "user_pk")
    )
    private Set<User> userSet;

    @OneToMany(mappedBy = "ride", fetch = FetchType.LAZY)
    private List<Report> reports;
}