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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car", cascade = CascadeType.ALL)
    @ToString.Include
    private Set<CarImage> carImages = new HashSet<>();


    public void addCarImage(CarImage carImage) {
        carImages.add(carImage);
        carImage.setCar(this);
    }

        public boolean deleteCarImage (CarImage carImage){
            if (carImages.contains(carImage)) {
                carImage.setCar(null);
                return carImages.remove(carImage);
            }
            return false;
        }

        public boolean removeCarImageV2 (CarImage carImage){
            return this.carImages.remove(carImage);
        }

        public boolean deleteCarImages (java.util.Collection < CarImage > carImages) {
            if (carImages.containsAll(carImages)) {
                System.out.println(carImages);
                return carImages.removeAll(carImages);
            }
            return false;
        }

    }