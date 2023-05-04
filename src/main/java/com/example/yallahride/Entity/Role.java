package com.example.yallahride.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_pk")
    private Long id;

    @Column(name = "role_name")
    @NonNull
    private String rollName;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "User_Role",
            joinColumns = @JoinColumn(name = "role_fk", referencedColumnName = "role_pk"),
            inverseJoinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "user_pk")
    )
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return Objects.equals(getId(), role.getId()) && getRollName().equals(role.getRollName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRollName());
    }
}
