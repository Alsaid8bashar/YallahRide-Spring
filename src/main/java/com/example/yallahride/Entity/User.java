package com.example.yallahride.Entity;

import com.example.yallahride.Entity.Report.Report;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Objects;
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
    @Transient
    @JsonIgnore
    MultipartFile multipartFile;
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
    @Column(name = "about")
    private String about;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "gender")
    @NonNull
    private String gender;
    @Column(name = "Warnings")
    private long warnings;

    @Column(name = "isVerified")
    private boolean isVerified;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinTable(name = "User_Preference", joinColumns = @JoinColumn(name = "user_id_fk", referencedColumnName = "user_pk"), inverseJoinColumns = @JoinColumn(name = "travel_preference_fk", referencedColumnName = "id_pk"))
    private Set<TravelPreference> travelPreferences = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Include
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "user_pk"), inverseJoinColumns = @JoinColumn(name = "role_fk", referencedColumnName = "role_pk"))
    private Set<Role> roles = new HashSet<>();

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "report", cascade = CascadeType.ALL)
//    @ToString.Exclude
//    @JsonIgnore
//    private Set<Report> reports = new HashSet<>();


    public void addRole(Role role) {
        roles.add(role);
    }

    public boolean deleteRole(Role role) {
        return roles.remove(role);
    }


    public boolean addTravelPreferences(TravelPreference[] newTravelPreferences) {
        for (int x = 0; x < newTravelPreferences.length; x++) {
            travelPreferences.add(newTravelPreferences[x]);
        }
        return true;
    }

    public boolean deleteTravelPreference(TravelPreference travelPreference) {
        return travelPreferences.remove(travelPreference);
    }

    public boolean deleteAllTravelPreference() {
        travelPreferences.clear();
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
