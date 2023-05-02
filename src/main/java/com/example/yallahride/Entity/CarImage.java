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
@Table(name = "Car_Images")
public class CarImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pk")
    private Long id;
    @Column(name = "image_path", nullable = false)
    @NonNull
    private String imagePath;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_fk")
    @ToString.Exclude
    private Car car;
    @PreRemove
    private void deleteImageFromCar() {
        car.deleteCarImage(this);
    }
}
