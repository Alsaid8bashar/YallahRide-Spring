package com.example.yallahride;


import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.CarService;
import com.example.yallahride.Service.Interface.UserService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class CarEntityTest {

    @Autowired
    CarService carService;
    @Autowired
    UserService userService;

    @Test
    @Order(1)
    public void testSaveCar() {
        User user = new User("Bashar", "ahamd", "basharalsaid17@gmail.com", "newImage");
        userService.saveUser(user);
        Car car = new Car();
        car.setMake("Ford");
        car.setModel("Fusion");
        car.setColor("black");
        car.setUser(user);
        carService.saveCar(car);
        java.util.List<CarImage> carImageList = new ArrayList<>();
        carImageList.add(new CarImage("newImageHasan"));
        carImageList.add(new CarImage("newImageBashar"));
        carService.addCarImages(car.getId(), carImageList);
        java.util.Set<CarImage> carImageList2 = car.getCarImages();
        System.out.println(carImageList2 + "hello");
        carService.deleteCarImages(car.getId(), carImageList2);
//        carService.deleteCarImages(car.getId());
    }

    @Test
    @Order(1)
    public void testDeleteCarImage() {
        Car car = carService.findCarById(80L).get();
        System.out.println("car.getMake() = " + car.getMake());
    }

}