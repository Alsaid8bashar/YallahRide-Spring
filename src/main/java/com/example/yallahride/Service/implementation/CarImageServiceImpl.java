package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Repository.CarImageRepository;
import com.example.yallahride.Service.Interface.CarImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarImageServiceImpl implements CarImageService {
    final CarImageRepository carImageRepository;

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


}
