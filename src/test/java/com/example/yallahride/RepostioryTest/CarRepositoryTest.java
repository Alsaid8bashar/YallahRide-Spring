package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Repository.CarRepository;
import com.example.yallahride.Repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;
    Car car;

    @BeforeAll
    public void setup() {
        User user = userRepository.save(new User("Hassan", "Al-Shannag", "shnaqhassan@hotmail.com", "image1"));

        Car car = new Car();
        car.setMake("Ford");
        car.setModel("Fusion");
        car.setColor("black");
        car.setUser(user);
        carRepository.save(car);

    }

    @Test
    @Order(1)
    public void testCreateCar() {
        User user = userRepository.save(new User("Hassan", "Al-Shannag", "shnaqhassan@hotmail.com", "image1"));

        Car car = new Car();
        car.setMake("Ford");
        car.setModel("Fusion");
        car.setColor("black");
        car.setUser(user);
        carRepository.save(car);
        Assertions.assertTrue(carRepository.findAll().contains(car));
    }


    @Test
    @Order(2)
    public void testFindCarById() {
        Optional<Car> optionalCar = carRepository.findById(car.getId());
        Car tempCar = optionalCar.get();
        Assertions.assertEquals(tempCar.getId(), car.getId());
    }


    @Test
    @Order(3)
    @Rollback(value = false)
    public void testFindAllPages() {
        List<Car> cars = carRepository.findAll();
        Assertions.assertTrue(cars.size() > 0);
    }

    @Test
    @Order(5)
    public void testDeletePageByID() {
        carRepository.deleteById(car.getId());
        Assertions.assertFalse(carRepository.findAll().contains(car));
    }

    @Test
    @Order(6)
    public void testDeleteAllCars() {
        carRepository.deleteAll();
        Assertions.assertTrue(carRepository.count() == 0);
    }
}
