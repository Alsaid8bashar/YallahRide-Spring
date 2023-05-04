package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Repository.PageVideoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PageContentRepositoryTest {
    @Autowired
    PageVideoRepository pageVideoRepository;
    PageVideo pageVideo;

    @BeforeAll
    public void setup() {
        pageVideo = pageVideoRepository.save(new PageVideo("VideoPath1"));
    }

    @Test
    @Order(1)
    public void testCreateRide() {
        PageVideo pageVideo = pageVideoRepository.save(new PageVideo("VideoPath2"));
        Assertions.assertTrue(pageVideo.getId() > 0);
    }


    @Test
    @Order(2)
    public void testFindRideById() {
        Optional<PageVideo> optionalRide = pageVideoRepository.findById(pageVideo.getId());
        PageVideo tempRide = optionalRide.get();
        Assertions.assertEquals(tempRide.getId(), pageVideo.getId());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testUpdateRide() {
        Optional<PageVideo> optionalRide = pageVideoRepository.findById(pageVideo.getId());
        pageVideo.setVideoPath("UpdatedVideoPath");
        PageVideo rideUpdated = pageVideoRepository.save(pageVideo);
        Assertions.assertEquals(pageVideo.getVideoPath(), rideUpdated.getVideoPath());
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void testFindAllRides() {
        List<PageVideo> rideList = pageVideoRepository.findAll();
        Assertions.assertTrue(rideList.size() > 0);
    }

    @Test
    @Order(5)
    public void testDeleteRideByID() {
        pageVideoRepository.deleteById(pageVideo.getId());
        Optional<PageVideo> optionalRide = pageVideoRepository.findById(pageVideo.getId());
        Assertions.assertTrue(!optionalRide.isPresent());
    }

    @Test
    @Order(6)
    public void testDeleteAllRides() {
        pageVideoRepository.deleteAll();
        Assertions.assertTrue(pageVideoRepository.count() == 0);
    }
}
