package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.CarImage;

import java.util.List;
import java.util.Optional;

public interface CarImageService {

    CarImage saveCarImage(CarImage car);

    Optional<CarImage> findCarImageById(Long id);

    List<CarImage> findAllCarImages();

    void deleteAllCarImages();
    void deleteCarImageById(Long id);

}
