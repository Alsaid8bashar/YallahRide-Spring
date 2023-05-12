package com.example.yallahride.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`User`")
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
    @Column(name = "image_path")
    private String imagePath;
    @Transient
    MultipartFile multipartFile;
    @Column(name = "about")
    @NonNull
    private String about;
    @Column(name = "is_active")
    private boolean isActive;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnore
    @ToString.Exclude
    @JoinTable(name = "User_Preference", joinColumns = @JoinColumn(name = "user_id_fk", referencedColumnName = "user_pk"), inverseJoinColumns = @JoinColumn(name = "travel_preference_fk", referencedColumnName = "id_pk"))
    private Set<TravelPreference> travelPreferences = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonIgnore
    @ToString.Include
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "user_pk"), inverseJoinColumns = @JoinColumn(name = "role_fk", referencedColumnName = "role_pk"))
    private Set<Role> roles = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"driver"}, allowSetters = true)
    @ToString.Include
    @JoinTable(name = "Passenger", joinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "user_pk"), inverseJoinColumns = @JoinColumn(name = "ride_fk", referencedColumnName = "ride_pk"))
    private Set<Ride> rides = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    public boolean deleteRole(Role role) {
        return roles.remove(role);
    }

    public boolean addRide(Ride ride) {
        return rides.add(ride);
    }


    public boolean deleteRide(Ride ride) {
        return rides.remove(ride);
    }

    public boolean addTravelPreference(TravelPreference travelPreference) {
        return travelPreferences.add(travelPreference);
    }

    public boolean deleteTravelPreference(TravelPreference travelPreference) {
        return travelPreferences.remove(travelPreference);
    }
}
