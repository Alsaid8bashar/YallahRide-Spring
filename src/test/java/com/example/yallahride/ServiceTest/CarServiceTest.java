package com.example.yallahride.ServiceTest;


import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.CarImageService;
import com.example.yallahride.Service.Interface.CarService;
import com.example.yallahride.Service.Interface.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarServiceTest {

    @Autowired
    CarService carService;
    @Autowired
    UserService userService;
    @Autowired
    CarImageService carImageService;

    Car car;


    @BeforeEach
    public void setCar() {
        User user = new User("Hassan", "Al-Shannag", "shnaqhassan@hotmail.com", "image1");
        car = new Car("Black", "Ford", "Fusion", "19-89893", 2014, user);
        carService.saveCar(car);
    }

    @Test
    @Order(3)
    public void testAddCarImage() {
        CarImage carImage = new CarImage();
        carImage.setCar(car);
        carImage.setImagePath("newImage");
        CarImage carImage2 = new CarImage();
        carImage.setCar(car);
        carImage2.setImagePath("newImage2");
        carService.addCarImage(car.getId(), carImage);
        carService.addCarImage(car.getId(), carImage2);
        System.out.println("carService.getAllCarImages(car.getId()) = " + carService.getAllCarImages(car.getId()));
        Assertions.assertThat(carService.getAllCarImages(car.getId())).isNotEmpty();
    }

    @Test
    @Order(4)
    public void testDeleteCarImage() {
        Long imageId = car.getCarImages().iterator().next().getId();

        carService.deleteCarImage(imageId);
        Assertions.assertThat(carImageService.findCarImageById(imageId) == null);
    }

}