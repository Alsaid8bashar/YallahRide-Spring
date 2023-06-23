package com.example.yallahride.Entity;

import com.example.yallahride.Entity.Enum.RideStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "departureDate")
    @NonNull
    private Date departureDate;
    @NonNull
    @Column(name = "seats")
    private int seats;
    @NonNull
    @Column(name = "cost")
    private double cost;
    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk", referencedColumnName = "user_pk")
    @NonNull
    private User driver;

//    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "ride", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    @JsonIgnore
//    private Set<Report> reports = new HashSet<>();

    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinColumn(name = "car_fk", referencedColumnName = "car_pk")
    @NonNull
    private Car car;

    @Column(name = "arrivalTime")
    @JsonFormat(pattern = "h:mm a")
    @NonNull
    private LocalTime arrivalTime;

    @Column(name = "departureTime")
    @JsonFormat(pattern = "h:mm a")
    @NonNull
    private LocalTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "arrivalData")
    @NonNull
    private Date arrivalDate;


    @JsonCreator
    public Ride(@NonNull String from, @NonNull String to, boolean isInstantBooking, RideStatus rideStatus, @NonNull Date departureDate, double cost, @NonNull User driver, @NonNull Car car, @NonNull LocalTime arrivalTime, @NonNull LocalTime departureTime, @NonNull Date arrivalDate) {
        this.from = from;
        this.to = to;
        this.isInstantBooking = isInstantBooking;
        this.rideStatus = rideStatus;
        this.departureDate = departureDate;
        this.cost = cost;
        this.driver = driver;
        this.car = car;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ride ride)) return false;
        return getSeats() == ride.getSeats() && Objects.equals(getId(), ride.getId()) && getFrom().equals(ride.getFrom()) && getTo().equals(ride.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFrom(), getTo(), getDepartureDate(), getSeats());
    }

}