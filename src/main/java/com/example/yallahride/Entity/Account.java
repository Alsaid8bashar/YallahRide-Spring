package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "Account")
public class Account {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    @JoinColumn(name = "user_id_fk")
    private User user;

    @Column(name = "is_Active")
    private Boolean isActive;
}
