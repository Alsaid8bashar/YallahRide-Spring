//package com.example.yallahride;
//
//
//import com.example.yallahride.Entity.Car;
//import com.example.yallahride.Entity.CarImage;
//import com.example.yallahride.Entity.User;
//import com.example.yallahride.Repository.CarRepository;
//import com.example.yallahride.Service.Interface.CarImageService;
//import com.example.yallahride.Service.Interface.CarService;
//import com.example.yallahride.Service.Interface.UserService;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//public class CarEntityTest {
//
//    @Autowired
//    CarService carService;
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    CarImageService carImageService;
//
//    @Autowired
//    CarRepository carRepository;
//
//    @Test
//    @Order(1)
//    public void testSaveCar() {
////        //creating a user
////        User user = new User("Bashar", "ahamd", "basharalsaid17@gmail.com", "newImage");
////        userService.saveUser(user);
////
////        //creating a car
////        Car car = new Car();
////        car.setMake("Ford");
////        car.setModel("Fusion");
////        car.setColor("black");
////        car.setUser(user);
////        carService.saveCar(car);
////
////
////
////        //create images
////        List<CarImage> carImageList = new ArrayList<>();
////        carImageList.add(new CarImage("newImageHasan"));
////        carImageList.add(new CarImage("newImageBashar"));
////        carService.addCarImages(car.getId(), carImageList);
////        carService.saveCar(car);
////
////        Car car2 = carService.findCarById(144L).get();
////        car2.getCarImages().remove(car2.getCarImages().iterator().next());
////        carRepository.flush();
//
//    carService.removeCarImage(137L);
//    carImageService.removeImageTEST(137L);
//
//
//    }
//
//    @Test
//    @Order(1)
//    public void testDeleteCarImage() {
//        Car car = carService.findCarById(80L).get();
//        System.out.println("car.getMake() = " + car.getMake());
//    }
//
//}