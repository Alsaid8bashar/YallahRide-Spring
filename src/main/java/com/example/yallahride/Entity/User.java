package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pk", nullable = false)
    private Long userPk;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "image_path")
    private String imagePath;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_fk")
    private Car car;
    @ManyToMany
    @JoinTable(
            name = "User_Preference",
            joinColumns = @JoinColumn(name = "user_id_fk", referencedColumnName = "user_pk"),
            inverseJoinColumns = @JoinColumn(name = "travel_preference_fk", referencedColumnName = "id_pk")
    )
    private Set<TravelPreference> travelPreferences;
    @OneToMany(mappedBy = "rater")
    private java.util.List<Rate> givenRates;
}
