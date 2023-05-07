package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.CarRepository;
import com.example.yallahride.Service.Interface.CarImageService;
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

    final private CarRepository carRepository;

    final private CarImageService carImageService;

    public CarServiceImpl(CarRepository carRepository, CarImageService carImageService) {
        this.carRepository = carRepository;
        this.carImageService = carImageService;
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car findCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        return unwrapCar(car,id);
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
    public Collection<CarImage> getAllCarImages(Long carId) {
        Optional<Car>car = carRepository.findById(carId);
        return unwrapCar(car,carId).getCarImages();
    }

    @Override
    public Car addCarImage(Long carId, CarImage carImage) {
        Car car = unwrapCar(carRepository.findById(carId),carId);
        car.addCarImage(carImage);
        return saveCar(car);
    }

    @Override
    public void deleteCarImage(Long id) {
        carImageService.deleteCarImageById(id);
    }

    @Override
    public long getNumberOfCars() {
        return carRepository.count();
    }

    @Override
    public void deleteCarImages(Long id) {
        Car car = unwrapCar(carRepository.findById(id),id);
        Iterator<CarImage> carImageIterator = car.getCarImages().iterator();
        while (carImageIterator.hasNext()) {
            CarImage element = carImageIterator.next();
            carImageIterator.remove();
        }
        saveCar(car);
    }

    static Car unwrapCar(Optional<Car> entity, Long id){
        if(entity.isPresent()) return entity.get();
        throw new EntityNotFoundException(id, Car.class);
    }
}