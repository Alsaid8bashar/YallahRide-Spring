package com.example.yallahride.ServiceTest;


import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Service.Interface.CarImageService;
import com.example.yallahride.Service.Interface.CarService;
import com.example.yallahride.Service.Interface.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarServiceTest {

    @Autowired
    CarService carService;
    @Autowired
    UserService userService;
    @Autowired
    CarImageService carImageService;

    Car car;
    User user;


    @BeforeAll
    public void setup() {
        user = userService.saveUser(new User("Hassan", "Al-Shannag","male"));
        car = carService.saveCar(new Car("Black", "Ford", "Fusion",
                "19-$#$dd#sd$#", 2014, user));
    }

    @Test
    @Order(1)
    public void testAddCarImage() {
        CarImage carImage1 = new CarImage();
        carImage1.setMultipartFile(new MockMultipartFile("carImage1.png","carImage1.png".getBytes()));
        carImage1 = carImageService.saveCarImage(carImage1);
        carImage1.setCar(car);

        CarImage carImage2 = new CarImage();
        carImage2.setMultipartFile(new MockMultipartFile("carImage2.png", "carImage2.png".getBytes()));
        carImageService.saveCarImage(carImage2);
        carImage2 = carImageService.saveCarImage(carImage2);
        carImage2.setCar(car);




        carService.addCarImage(car.getId(), carImage1);
        carService.addCarImage(car.getId(), carImage2);

        car = carService.saveCar(car);

        System.out.println("car = " + car);
        Assertions.assertTrue(carService.getAllCarImages(car.getId()).size() > 0);
    }

    @Test
    @Order(2)
    public void testDeleteCarImage() {
        car = carService.findCarById(car.getId());
        Long carImageId = car.getCarImages().iterator().next().getId();
        carService.deleteCarImage(carImageId);
        Assertions.assertThrowsExactly(EntityNotFoundException.class, () ->
                carImageService.findCarImageById(carImageId));
    }

    @Test
    @Order(3)
    public void should_not_remove_parent_when_child_removed(){
        carService.deleteCarById(car.getId());
        Assertions.assertTrue(userService.findUserById(user.getId()).getId() > 0);
    }

    @AfterAll
    public void cleanup() {
        userService.deleteUserById(user.getId());
    }
}