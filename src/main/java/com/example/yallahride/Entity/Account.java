package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "Account")
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_pk")
    private Long id;

    @NonNull
    @Column(name = "email")
    private String email;
    @Lob
    @NonNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @NonNull
    @Column(name = "password_hash", length = 1000)
    private String passwordHash;

    @OneToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_pk")
    private User user;

    @Column(name = "is_Active")
    private Boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getUser().getRoles();
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
