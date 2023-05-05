package com.example.yallahride.Controller;

import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Service.Interface.CarImageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("car_image")
public class CarImageController {

    private final CarImageService carImageService;

    public CarImageController(CarImageService carImageService) {
        this.carImageService = carImageService;
    }
    @GetMapping("/car_image/{id}")
    public ResponseEntity<CarImage> getCarImage(@PathVariable Long id) {
        return new ResponseEntity<>(carImageService.findCarImageById(id).get(), OK);
    }

    @PostMapping("/save_car_image")
    public ResponseEntity<CarImage> saveCarImage(@Valid @RequestBody CarImage carImage) {
        return new ResponseEntity<>(carImageService.saveCarImage(carImage), CREATED);
    }

    @DeleteMapping("/delete_car_image/{id}")
    public ResponseEntity<HttpStatus> deleteCarImage(@PathVariable Long id) {
        carImageService.deleteCarImageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarImage>> getCars() {
        return new ResponseEntity<>(carImageService.findAllCarImages(), HttpStatus.OK);
    }
}
