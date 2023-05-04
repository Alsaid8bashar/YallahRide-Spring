package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Repository.CarImageRepository;
import com.example.yallahride.Repository.CarRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarImageRepositoryTest {

    @Autowired
    CarImageRepository carImageRepository;
    CarImage carImage;
    User user;
    Car car;

    @BeforeAll
    public void setup() {
        user = new User("Hassan", "Al-Shannag", "shnaqhassan@hotmail.com", "image1");
        car = new Car("Black", "Ford", "Fusion", "19-89893", 2014, user);
        carImage = new CarImage("carImagePath1");
        carImage.setCar(car);
        carImage = carImageRepository.save(carImage);
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void testCreateCarImage() {
        Assertions.assertTrue(carImage.getId() > 0);
    }


    @Test
    @Order(2)
    public void testFindCarImageById() {
        Optional<CarImage> optionalCarImage = carImageRepository.findById(carImage.getId());
        CarImage tempCarImage = optionalCarImage.get();
        Assertions.assertEquals(tempCarImage.getId(), carImage.getId());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testUpdateCarImage() {
        Optional<CarImage> optionalCarImage = carImageRepository.findById(carImage.getId());
        carImage.setImagePath("UpdatedCarImagePath");
        CarImage carImageUpdated = carImageRepository.save(carImage);
        Assertions.assertEquals(carImage.getImagePath(), carImageUpdated.getImagePath());
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void testFindAllCarImages() {
        List<CarImage> carImageList = carImageRepository.findAll();
        Assertions.assertTrue(carImageList.size() > 0);
    }

    @Test
    @Order(5)
    public void testDeleteCarImageByID() {
        carImageRepository.deleteById(carImage.getId());
        Optional<CarImage> optionalCarImage = carImageRepository.findById(carImage.getId());
        Assertions.assertTrue(!optionalCarImage.isPresent());
    }

    @Test
    @Order(6)
    public void testDeleteAllCarImages() {
        List<CarImage>carImages = carImageRepository.findAll();
        System.out.println("carImages = " + carImages);
        carImageRepository.deleteAll();
        Assertions.assertTrue(carImageRepository.count() == 0);
    }
}
