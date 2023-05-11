package com.example.yallahride.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_pk")
    private Long id;
    @NonNull
    private String color;
    @NonNull
    private String make;
    @NonNull
    private String model;


    @NotBlank
    @Column(name = "license_plate")
    @NonNull
    private String licensePlate;

    @Column(name = "model_year")
    @NonNull
    private int modelYear;
    @OneToOne()
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_pk")
    @ToString.Exclude
    @JsonIgnore
    @NonNull
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<CarImage> carImages = new HashSet<>();


    public void addCarImage(CarImage carImage) {
        carImages.add(carImage);
        carImage.setCar(this);
    }

    public boolean deleteCarImage(CarImage carImage) {
        if (carImages.contains(carImage)) {
            carImage.setCar(null);
            return carImages.remove(carImage);
        }
        return false;
    }

    public boolean removeCarImageV2(CarImage carImage) {
        return this.carImages.remove(carImage);
    }

    public boolean deleteCarImages(java.util.Collection<CarImage> carImages) {
        if (carImages.containsAll(carImages)) {
            System.out.println(carImages);
            return carImages.removeAll(carImages);
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return getModelYear() == car.getModelYear() && Objects.equals(getId(), car.getId()) && getColor().equals(car.getColor()) && getMake().equals(car.getMake()) && getModel().equals(car.getModel()) && getLicensePlate().equals(car.getLicensePlate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getColor(), getMake(), getModel(), getLicensePlate(), getModelYear());
    }
}