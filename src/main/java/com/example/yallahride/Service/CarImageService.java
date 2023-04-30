package com.example.yallahride.Service;

import com.example.yallahride.Entity.CarImage;

import java.util.List;
import java.util.Optional;

public interface CarImageService {

    void saveCarImage(CarImage car);

    Optional<CarImage> findCarImageById(Long id);

    List<CarImage> findAllCarImages();

    void deleteAllCarImages();

}
