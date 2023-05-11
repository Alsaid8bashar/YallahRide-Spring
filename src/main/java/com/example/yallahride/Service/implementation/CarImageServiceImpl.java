package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.CarImageRepository;
import com.example.yallahride.Repository.CarRepository;
import com.example.yallahride.Service.Interface.CarImageService;
import com.example.yallahride.Service.Interface.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarImageServiceImpl implements CarImageService {
    final CarImageRepository carImageRepository;
    @Autowired
    CarRepository carRepository;

    @Autowired
    FileService fileService;

    public CarImageServiceImpl(CarImageRepository carImageRepository) {
        this.carImageRepository = carImageRepository;
    }

    @Override
    public CarImage saveCarImage(CarImage car) {
        return carImageRepository.save(car);
    }

    @Override
    public CarImage findCarImageById(Long id) {
        Optional<CarImage> carImage = carImageRepository.findById(id);
        return unwrapUser(carImage, id);
    }

    @Override
    public List<CarImage> findAllCarImages() {
        return carImageRepository.findAll();
    }

    @Override
    public void deleteAllCarImages() {
        carImageRepository.deleteAll();
    }

    @Override
    public void deleteCarImageById(Long id) {
        CarImage carImage = unwrapUser(carImageRepository.findById(id),id);
        fileService.deleteFile(carImage.getImagePath(), "car-bucket");

        carImageRepository.deleteById(id);
    }

    static CarImage unwrapUser(Optional<CarImage> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, CarImage.class);
    }


}
