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
        car = carService.findCarById(181L).get();
    }

    @Test
    @Order(1)
    public void testSaveCar() {
        User user = new User("Bashar", "ahmad", "basharalsaid17@gmail.com", "newImage");
        userService.saveUser(user);
        Car car = new Car();
        car.setMake("Ford");
        car.setModel("Fusion");
        car.setColor("black");
        car.setUser(user);
        carService.saveCar(car);

        Car savedCar = carService.findCarById(car.getId()).get();
        Assertions.assertThat(savedCar).isNotNull();
        Assertions.assertThat(savedCar.getMake()).isEqualTo("Ford");
        Assertions.assertThat(savedCar.getModel()).isEqualTo("Fusion");
        Assertions.assertThat(savedCar.getColor()).isEqualTo("black");

    }

    @Test
    @Order(2)
    public void testUpdateCar() {
        car.setColor("red");
        carService.saveCar(car);
        Car updatedCar = carService.findCarById(car.getId()).get();
        Assertions.assertThat(updatedCar).isNotNull();
        Assertions.assertThat(updatedCar.getColor()).isEqualTo("red");
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
        Assertions.assertThat(carService.getAllCarImages(car.getId())).isNotEmpty();
    }

    @Test
    @Order(4)
    public void testDeleteCarImage() {
        Long imageId = car.getCarImages().iterator().next().getId();

        carService.deleteCarImage(imageId);
        Assertions.assertThat(carImageService.findCarImageById(imageId)).isEmpty();
    }

}