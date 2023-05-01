package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CarService {

    void saveCar(Car car);


    Optional<Car> findCarById(Long id);

    List<Car> findAllCars();

    void deleteAllCars();

    void deleteCarById(Long id);

    long getNumberOfCars();

    void addCarImages(Long id, List<CarImage> carImages);

    void deleteCarImages(Long id, Collection<CarImage> carImages);

    void deleteCarImages(Long id);

//    List<CarImage> getCarsImagesByCarId(Long id);
}
