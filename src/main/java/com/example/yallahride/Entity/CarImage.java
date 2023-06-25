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
    @Transient
    @JsonIgnore
    @NonNull
    MultipartFile multipartFile;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pk")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_fk", referencedColumnName = "car_pk")
    @JsonIgnore
    private Car car;
    @Column(name = "image_path")
    private String imagePath;

    @PreRemove
    private void deleteImageFromCar() {
//        car.deleteCarImage(this);
    }
}
