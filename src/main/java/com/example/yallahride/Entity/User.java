package com.example.yallahride.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pk")
    private Long id;

    @Column(name = "first_name")
    @NonNull
    private String firstName;
    @Column(name = "last_name")
    @NonNull
    private String lastName;
    @Column(name = "email")
    @NonNull
    private String email;
    @Column(name = "image_path")
    @NonNull
    private String imagePath;
    @Column(name = "is_active")
    @Getter
    @GeneratedValue(strategy = GenerationType.TABLE)
    private boolean isActive;
    @ManyToMany
    @JsonIgnore
    @ToString.Exclude
    @JoinTable(
            name = "User_Preference",
            joinColumns = @JoinColumn(name = "user_id_fk", referencedColumnName = "user_pk"),
            inverseJoinColumns = @JoinColumn(name = "travel_preference_fk", referencedColumnName = "id_pk")
    )
    private Set<TravelPreference> travelPreferences;

    @ManyToMany
    @JsonIgnore
    @ToString.Exclude
    @JoinTable(
            name = "User_Role",
            joinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "user_pk"),
            inverseJoinColumns = @JoinColumn(name = "role_fk", referencedColumnName = "role_pk")
    )
    private Set<Role>roles;
    @ManyToMany
    @JsonIgnore
    @ToString.Exclude
    @JoinTable(
            name = "Passenger",
            joinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "user_pk"),
            inverseJoinColumns = @JoinColumn(name = "ride_fk", referencedColumnName = "ride_pk")
    )
    private Set<Ride>rides;
    public void addRole(Role role) {

    }


}
