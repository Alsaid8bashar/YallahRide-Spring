package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Entity.EntityListener.CarEventListener;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.CarRepository;
import com.example.yallahride.Service.Interface.CarImageService;
import com.example.yallahride.Service.Interface.CarService;
import com.example.yallahride.Service.Interface.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class CarServiceImpl implements CarService {

    final
    CarRepository carRepository;
    final
    CarImageService carImageService;
    final
    CarEventListener carEventListener;
    final
    FileService fileService;

    public CarServiceImpl(CarRepository carRepository, CarImageService carImageService, CarEventListener carEventListener, FileService fileService) {
        this.carRepository = carRepository;
        this.carImageService = carImageService;
        this.carEventListener = carEventListener;
        this.fileService = fileService;
    }


    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car saveCar(Car car, Collection<MultipartFile> multipartFiles) {
        for (MultipartFile multipartFile : multipartFiles) {
            CarImage image = new CarImage();
            String key=fileService.uploadFile(multipartFile);
            image.setKey(key);
            image.setImagePath(fileService.getObjectUrl(key));
            car.addCarImage(image);
        }
        return saveCar(car);
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
//        carEventListener.onCarRemoval(unwrapCar(carRepository.findById(id), id));
        carRepository.deleteById(id);
    }

    @Override
    public Collection<CarImage> getAllCarImages(Long carId) {
        Optional<Car> car = carRepository.findById(carId);
        return unwrapCar(car, carId).getCarImages();
    }

    @Override
    public Car addCarImage(Long carId, CarImage carImage) {
        Car car = saveImage(carId, carImage);
//        return saveCar(car);
        return null;
    }

    @Override
    public Car addCarImages(Long carId, Collection<CarImage> carImages) {
        Car car = null;
        for (CarImage carImage : carImages) {
            car = saveImage(carId, carImage);
        }
        return car;
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
    public Collection<Car> getUserCars(long id) {
        return carRepository.findByUser_Id(id);
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
//        saveCar(car);
    }

    private Car saveImage(Long carId, CarImage carImage) {
        String key=fileService.uploadFile(carImage.getMultipartFile());
        carImage.setImagePath(fileService.getObjectUrl(key));
        Car car = unwrapCar(carRepository.findById(carId), carId);
        car.addCarImage(carImage);
        return car;
    }

    private Car unwrapCar(Optional<Car> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        throw new EntityNotFoundException(id, Car.class);
    }
}