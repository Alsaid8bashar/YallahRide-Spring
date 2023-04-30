package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private User user;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<CarImage> carImages;

    @Transactional
    public void addCarImage(CarImage carImage) {
        if (carImages == null) {
            carImages = new ArrayList<>();
        }
        carImages.add(carImage);
    }

    public boolean deleteCarImage(CarImage carImage) {
        return carImages.remove(carImage);
    }


}
