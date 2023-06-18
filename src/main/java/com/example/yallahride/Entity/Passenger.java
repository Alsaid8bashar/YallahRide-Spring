package com.example.yallahride.Entity;

import com.example.yallahride.Entity.Enum.RideStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "Passenger")
public class Passenger {
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_fk")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @NonNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "ride_fk")
    private Ride ride;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_pk")
    private Long id;
    @Column(name = "is_Accepted")
    private boolean isAccepted;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    @JsonCreator
    public Passenger(@NonNull @JsonProperty("_user") User user, @NonNull @JsonProperty("_ride") Ride ride, @NonNull @JsonProperty("_rideStatus") RideStatus rideStatus) {
        this.user = user;
        this.ride = ride;
        this.rideStatus=rideStatus;
    }

    @PreRemove
    public void notifyUser() {
        //TODO notify the user when the driver reject the request
    }
}
