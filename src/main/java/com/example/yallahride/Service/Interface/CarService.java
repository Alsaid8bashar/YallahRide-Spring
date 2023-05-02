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

    Collection<CarImage> getAllCarImages(Long carId);

    long getNumberOfCars();


    void addCarImage(Long carId, CarImage carImage);

    void deleteCarImages(Long id);

    void deleteCarImage(Long id);

}