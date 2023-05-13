package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Repository.PageRepository;
import com.example.yallahride.Repository.PageVideoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PageVideoRepositoryTest {
    @Autowired
    PageVideoRepository pageVideoRepository;
    @Autowired
    PageRepository pageRepository;
    PageVideo pageVideo;
    Page page;

    @BeforeAll
    public void setup() {
        page = pageRepository.save(new Page());
        pageVideo = pageVideoRepository.save(new PageVideo());
        pageVideo.setPage(page);
        pageVideoRepository.save(pageVideo);
    }

    @Test
    @Order(1)
    public void testCreatePageVideo() {
        Assertions.assertTrue(pageVideo.getId() > 0);
    }


    @Test
    @Order(2)
    public void testFindPageVideoById() {
        Optional<PageVideo> optionalPageVideo = pageVideoRepository.findById(pageVideo.getId());
        PageVideo tempPageVideo = optionalPageVideo.get();
        Assertions.assertEquals(tempPageVideo.getId(), pageVideo.getId());
    }


    @Test
    @Order(3)
    @Rollback(value = false)
    public void testFindAllPageVideos() {
        List<PageVideo> pageVideos = pageVideoRepository.findAll();
        Assertions.assertTrue(pageVideos.size() > 0);
    }

    @Test
    @Order(4)
    public void testDeletePageByID() {
        pageVideoRepository.deleteById(pageVideo.getId());
        Assertions.assertFalse(pageVideoRepository.findAll().contains(pageVideo));
    }

    @Test
    @Order(5)
    public void testDeleteAllPages() {
        pageVideoRepository.deleteAll();
        Assertions.assertTrue(pageVideoRepository.count() == 0);
    }
}
