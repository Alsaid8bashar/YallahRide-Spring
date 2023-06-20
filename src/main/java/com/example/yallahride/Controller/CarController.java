package com.example.yallahride.Controller;


import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Service.Interface.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.Optional;

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
        return new ResponseEntity<>(carService.findCarById(id), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Car> saveCar(@RequestPart(value = "car") Car car,
                                       @Optional(value = "carImages") MultipartFile[] carImages) {
        return new ResponseEntity<>(carService.saveCar(car,carImages), OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<>(carService.findAllCars(), HttpStatus.OK);
    }


    @PostMapping("/add/image")
    public ResponseEntity<Car> addCarImage(@RequestParam Long id, @ModelAttribute CarImage carImage) {
        return new ResponseEntity<>(carService.addCarImage(id, carImage), CREATED);
    }

    @GetMapping("/all/images")
    public ResponseEntity<Collection<CarImage>> getCarImages(@RequestParam Long carId) {
        return new ResponseEntity<>(carService.getAllCarImages(carId), HttpStatus.OK);
    }

    @GetMapping("/user/{carId}")
    public ResponseEntity<Collection<Car>> getUserCar(@PathVariable Long carId) {
        return new ResponseEntity<>(carService.getUserCars(carId), HttpStatus.OK);
    }

    @DeleteMapping("/delete-image/{imageId}")
    public ResponseEntity<HttpStatus> deleteCarImage(@PathVariable Long imageId) {
        carService.deleteCarImage(imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus> deleteCars() {
        carService.deleteAllCars();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Long> getNumberOfCars() {
        return new ResponseEntity<>(carService.getNumberOfCars(), HttpStatus.OK);
    }


}
