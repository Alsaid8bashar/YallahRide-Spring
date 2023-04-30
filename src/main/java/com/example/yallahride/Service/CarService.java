package com.example.yallahride.Service;

import com.example.yallahride.Entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    void saveCar(Car car);


    Optional<Car> findCarById(Long id);

    List<Car> findAllCars();

    void deleteAllCars();

    void deleteCarById(Long id);

    long getNumberOfCars();

//    List<CarImage> getCarsImagesByCarId(Long id);
}
