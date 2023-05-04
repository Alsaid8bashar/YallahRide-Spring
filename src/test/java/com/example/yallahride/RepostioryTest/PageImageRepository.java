package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Repository.PageImagesRepository;
import com.example.yallahride.Repository.PageRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PageImageRepository {
    @Autowired
    PageRepository pageRepository;
    @Autowired
    PageImagesRepository pageImagesRepository;
    PageImage pageImage;
    Page page;

    @BeforeAll
    public void setup() {
        page = new Page();
        pageImage = new PageImage("newImage");
        pageImage.setPage(page);
        pageImagesRepository.save(pageImage);
    }

    @Test
    @Order(1)
    public void testCreatePageImage() {
        PageImage pageImage = new PageImage("newImage");
//        Page page = new Page();
        pageImage.setPage(page);
        pageImagesRepository.save(pageImage);
        Assertions.assertTrue(pageImagesRepository.findAll().contains(pageImage));
    }


    @Test
    @Order(2)
    public void testFindPageImageById() {
        Optional<PageImage> optionalPageImage = pageImagesRepository.findById(pageImage.getId());
        PageImage tempPageImage = optionalPageImage.get();
        Assertions.assertEquals(tempPageImage.getId(), pageImage.getId());
    }


    @Test
    @Order(3)
    @Rollback(value = false)
    public void testFindAllPageImages() {
        List<PageImage> pageImages = pageImagesRepository.findAll();
        Assertions.assertTrue(pageImages.size() > 0);
    }

    @Test
    @Order(4)
    public void testDeletePageByID() {
        pageImagesRepository.deleteById(pageImage.getId());
        Assertions.assertFalse(pageImagesRepository.findAll().contains(pageImage));
    }

    @Test
    @Order(5)
    public void testDeleteAllPages() {
        pageImagesRepository.deleteAll();
        Assertions.assertTrue(pageImagesRepository.count() == 0);
    }
}
