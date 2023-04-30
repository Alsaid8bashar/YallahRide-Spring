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
        CarImage carImage = new CarImage("newImage2/");
        carImage.setCar(car);
        car.addCarImage(carImage);
        CarImage carImage2 = new CarImage("newImage100/");
        car.addCarImage(carImage2);
        carImage2.setCar(car);
        carService.saveCar(car);
        System.out.println("car.deleteCarImage(carImage) = " + car.deleteCarImage(carImage));
        carImage.setCar(null);
        carService.saveCar(car);
    }

    @Test
    @Order(1)
    public void testDeleteCarImage() {


    }

}
