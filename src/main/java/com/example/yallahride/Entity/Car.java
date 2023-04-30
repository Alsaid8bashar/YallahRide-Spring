package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_pk", nullable = false)
    private Long carPk;
    @NonNull
    private String color, make, model;

    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @Column(name = "model_year", nullable = false)
    private int modelYear;
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_pk")
    private User user;
    @OneToMany(mappedBy = "car")
    private List<CarImage> carImages;



}
