package com.example.yallahride.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "id_pk")
    private Long id;
    @NonNull
    private String description;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "User_Preference",
            joinColumns = @JoinColumn(name = "travel_preference_fk", referencedColumnName = "id_pk"),
            inverseJoinColumns = @JoinColumn(name = "user_id_fk", referencedColumnName = "user_pk"))
    private Set<User> users;
}
