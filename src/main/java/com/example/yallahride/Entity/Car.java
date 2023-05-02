package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_pk", nullable = false)
    private Long id;
    @NonNull
    private String color;
    @NonNull
    private String make;
    @NonNull
    private String model;

    @Column(name = "license_plate", nullable = false)
    @NonNull
    private String licensePlate;

    @Column(name = "model_year", nullable = false)
    @NonNull
    private int modelYear;
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_pk")
    @ToString.Exclude
    private User user;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<CarImage> carImages = new HashSet<>();

    public void addCarImage(CarImage carImage) {
        carImages.add(carImage);
        carImage.setCar(this);
    }

    public void deleteCarImage(CarImage carImage) {
        carImages.remove(carImage);
    }

}