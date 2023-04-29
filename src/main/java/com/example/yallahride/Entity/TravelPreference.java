package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Travel_Preference")
public class TravelPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pk", nullable = false)
    private Long idPk;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "User_Preference",
            joinColumns = @JoinColumn(name = "user_id_fk", referencedColumnName = "user_pk"),
            inverseJoinColumns = @JoinColumn(name = "travel_preference_fk", referencedColumnName = "id_pk")
    )
    private Set<User> users;
}
