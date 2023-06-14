package com.example.yallahride.Entity;

import com.example.yallahride.Entity.Enum.RideStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
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
    @Column(name = "max_two_in_the_back")
    private boolean isMaxTwoInTheBook;
    @Column(name = "is_instant_booking")
    private boolean isInstantBooking;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "`date`")
    @NonNull
    private Date date;
    @NonNull
    @Column(name = "seats")
    private int seats;
    @NonNull
    @Column(name = "cost")
    private double cost;
    @ManyToOne
    @JoinColumn(name = "user_fk", referencedColumnName = "user_pk")
    @NonNull
    private User driver;

    @OneToMany(mappedBy = "ride", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Report> reports = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "car_fk", referencedColumnName = "car_pk")
    @NonNull
    private Car car;

    @Column(name = "arrivalTime")
    @JsonFormat(pattern = "HH:mm")
    @NonNull
    private LocalTime arrivalTime;

    @Column(name = "departureTime")
    @JsonFormat(pattern = "HH:mm")
    @NonNull
    private LocalTime departureTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ride ride)) return false;
        return getSeats() == ride.getSeats() && Objects.equals(getId(), ride.getId()) && getFrom().equals(ride.getFrom()) && getTo().equals(ride.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFrom(), getTo(), getDate(), getSeats());
    }
}