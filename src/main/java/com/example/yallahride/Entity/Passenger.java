package com.example.yallahride.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Passenger")
public class Passenger {
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_fk")
    User user;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @NonNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "ride_fk")
    Ride ride;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_pk")
    private Long id;
    @Column(name = "is_Accepted")
    private boolean isAccepted;

    @PreRemove
    public void notifyUser() {
        //TODO notify the user when the driver reject the request
    }
}
