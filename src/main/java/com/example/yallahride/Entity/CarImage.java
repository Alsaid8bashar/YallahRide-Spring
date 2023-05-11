package com.example.yallahride.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_fk")
    @JsonIgnore
    private Car car;
    @Transient
    MultipartFile multipartFile;

    @PreRemove
    private void deleteImageFromCar() {
        car.deleteCarImage(this);
    }
}
