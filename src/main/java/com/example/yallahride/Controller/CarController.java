package com.example.yallahride.Controller;


import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Service.Interface.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("car")
public class CarController {

    private final CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        return new ResponseEntity<>(carService.findCarById(id).get(), OK);
    }

    @PostMapping("/save_car")
    public ResponseEntity<Car> saveCar(@Valid @RequestBody Car car) {
        return new ResponseEntity<>(carService.saveCar(car), CREATED);
    }

    @DeleteMapping("/delete_car/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<>(carService.findAllCars(), HttpStatus.OK);
    }

    @PostMapping("/add_car_image")
    public ResponseEntity<Car> addCarImage(@RequestBody Long id, CarImage carImage) {
        return new ResponseEntity<>(carService.addCarImage(id, carImage), CREATED);
    }

    @GetMapping("/all_car_image")
    public ResponseEntity<Collection<CarImage>> getCarImages(@RequestParam Long carId) {
        return new ResponseEntity<>(carService.getAllCarImages(carId), HttpStatus.OK);
    }

    @DeleteMapping("delete_image/{imageId}")
    public ResponseEntity<HttpStatus> deleteCarImage(@PathVariable Long imageId) {
        carService.deleteCarImage(imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteCars")
    public ResponseEntity<HttpStatus> deleteCars() {
        carService.deleteAllCars();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/number_of_cars")
    public ResponseEntity<Long> getNumberOfCars() {
        return new ResponseEntity<>(carService.getNumberOfCars(), HttpStatus.OK);
    }

}
