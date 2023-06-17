package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;

import java.util.Collection;
import java.util.List;

public interface CarService {

    Car saveCar(Car car);


    Car findCarById(Long id);

    List<Car> findAllCars();

    void deleteAllCars();

    void deleteCarById(Long id);

    Collection<CarImage> getAllCarImages(Long carId);

    long getNumberOfCars();

     Collection<Car> getUserCars(long id);

    Car addCarImage(Long carId, CarImage carImage);

    Car addCarImages(Long carId, Collection<CarImage> carImage);

    void deleteCarImages(Long id);

    void deleteCarImage(Long id);

}