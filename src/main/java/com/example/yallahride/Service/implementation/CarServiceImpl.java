package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Entity.EntityListener.CarEventListener;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.CarRepository;
import com.example.yallahride.Service.Interface.CarImageService;
import com.example.yallahride.Service.Interface.CarService;
import com.example.yallahride.Service.Interface.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;
    @Autowired
    CarImageService carImageService;
    @Autowired
    CarEventListener carEventListener;
    @Autowired
    FileService fileService;




    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car findCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        return unwrapCar(car, id);
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
        carEventListener.onCarRemoval(unwrapCar(carRepository.findById(id), id));
        carRepository.deleteById(id);
    }

    @Override
    public Collection<CarImage> getAllCarImages(Long carId) {
        Optional<Car> car = carRepository.findById(carId);
        return unwrapCar(car, carId).getCarImages();
    }

    @Override
    public Car addCarImage(Long carId, CarImage carImage) {
        carImage.setImagePath(fileService.uploadFile(carImage.getMultipartFile()));
        Car car = unwrapCar(carRepository.findById(carId), carId);
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
        Car car = unwrapCar(carRepository.findById(id), id);
        Iterator<CarImage> carImageIterator = car.getCarImages().iterator();
        while (carImageIterator.hasNext()) {
            CarImage element = carImageIterator.next();
            fileService.deleteFile(element.getImagePath());
            carImageIterator.remove();
        }
        saveCar(car);
    }

    static Car unwrapCar(Optional<Car> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        throw new EntityNotFoundException(id, Car.class);
    }
}