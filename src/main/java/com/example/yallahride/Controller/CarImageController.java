package com.example.yallahride.Controller;

import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Service.Interface.CarImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("car-image")
public class CarImageController {

    private final CarImageService carImageService;

    public CarImageController(CarImageService carImageService) {
        this.carImageService = carImageService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarImage> getCarImage(@PathVariable Long id) {
        return new ResponseEntity<>(carImageService.findCarImageById(id).get(), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CarImage> saveCarImage(@RequestBody CarImage carImage) {
        return new ResponseEntity<>(carImageService.saveCarImage(carImage), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCarImage(@PathVariable Long id) {
        carImageService.deleteCarImageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarImage>> getCarsImages() {
        return new ResponseEntity<>(carImageService.findAllCarImages(), HttpStatus.OK);
    }
}
