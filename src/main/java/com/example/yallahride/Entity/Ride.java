package com.example.yallahride.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

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

    @Column(name = "`date`")
    @CreationTimestamp
    private Date date;
    @NonNull
    @Column(name = "seats")
    private int seats;
    @ManyToOne
    @JoinColumn(name = "user_fk", referencedColumnName = "user_pk")
    @NonNull
    private User driver;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    @JoinTable(
            name = "Passenger",
            joinColumns = @JoinColumn(name = "ride_fk", referencedColumnName = "ride_pk"),
            inverseJoinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "user_pk")
    )
    private Set<User> userSet = new HashSet<>();

    @OneToMany(mappedBy = "ride", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Report> reports = new HashSet<>();


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