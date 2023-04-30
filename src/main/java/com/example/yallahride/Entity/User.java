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
    @Column(name = "user_pk")
    private Long userPk;

    @Column(name = "first_name")
    @NonNull
    private String firstName;
    @Column(name = "last_name")
    @NonNull
    private String lastName;
    private String email;
    @Column(name = "image_path")
    @NonNull
    private String imagePath;
    @ManyToMany
    @JoinTable(
            name = "User_Preference",
            joinColumns = @JoinColumn(name = "user_id_fk", referencedColumnName = "user_pk"),
            inverseJoinColumns = @JoinColumn(name = "travel_preference_fk", referencedColumnName = "id_pk")
    )
    private Set<TravelPreference> travelPreferences;
}
