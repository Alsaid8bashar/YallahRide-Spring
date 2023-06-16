package com.example.yallahride.Entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "Feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_pk")
    private Long id;
    @Column(name = "feedback")
    private String feedback;
    @Column(name = "stars")
    private int starts;
    @OneToOne(cascade = CascadeType.ALL)
    @NonNull
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_pk")
    private User user;
}
