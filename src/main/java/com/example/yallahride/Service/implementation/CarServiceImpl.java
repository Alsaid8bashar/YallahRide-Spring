package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Repository.CarRepository;
import com.example.yallahride.Service.Interface.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CarServiceImpl implements CarService {

    final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public Optional<Car> findCarById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void deleteAllCars() {
        carRepository.deleteAll();
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public long getNumberOfCars() {
        return carRepository.count();
    }


    @Override
    public void addCarImages(Long carId, List<CarImage> carImages) {
        Car car = findCarById(carId).get();
        for (CarImage carImage : carImages) {
            car.addCarImage(carImage);
        }
        saveCar(car);
    }

    @Override
    public void deleteCarImages(Long id, Collection<CarImage> carImages) {
        Car car = findCarById(id).get();
        car.deleteCarImages(carImages);
        saveCar(car);
    }

    @Override
    public void deleteCarImages(Long id) {
        Car car = findCarById(id).get();
        Iterator<CarImage> carImageIterator = car.getCarImages().iterator();
        while (carImageIterator.hasNext()) {
            CarImage element = carImageIterator.next();
            carImageIterator.remove();
        }
        saveCar(car);
    }

    @Override
    public void removeCarImage(Long id) {

    }

}