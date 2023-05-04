package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Repository.CarImageRepository;
import com.example.yallahride.Repository.CarRepository;
import com.example.yallahride.Service.Interface.CarImageService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CarImageServiceImpl implements CarImageService {
    final CarImageRepository carImageRepository;
    @Autowired
    CarRepository carRepository;

    public CarImageServiceImpl(CarImageRepository carImageRepository) {
        this.carImageRepository = carImageRepository;
    }

    @Override
    public void saveCarImage(CarImage car) {
        carImageRepository.save(car);
    }

    @Override
    public Optional<CarImage> findCarImageById(Long id) {
        return carImageRepository.findById(id);
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
        carImageRepository.deleteById(id);
    }




}
