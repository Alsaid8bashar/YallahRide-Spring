package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Repository.PageRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PageRepositoryTest {

    @Autowired
    PageRepository pageRepository;
    Page page;

    @BeforeAll
    public void setup() {
        page = pageRepository.save(new Page());
    }

    @Test
    @Order(1)
    public void testCreatePage() {
        Page page = pageRepository.save(new Page());
        Assertions.assertTrue(pageRepository.findAll().contains(page));
    }


    @Test
    @Order(2)
    public void testFindPageById() {
        Optional<Page> optionalPage = pageRepository.findById(page.getId());
        Page tempPage = optionalPage.get();
        Assertions.assertEquals(tempPage.getId(), page.getId());
    }


    @Test
    @Order(3)
    @Rollback(value = false)
    public void testFindAllPages() {
        List<Page> pages = pageRepository.findAll();
        Assertions.assertTrue(pages.size() > 0);
    }

    @Test
    @Order(5)
    public void testDeletePageByID() {
        pageRepository.deleteById(page.getId());
        Assertions.assertFalse(pageRepository.findAll().contains(page));
    }
    @Test
    @Order(6)
    public void testDeleteAllPages() {
        pageRepository.deleteAll();
        Assertions.assertTrue(pageRepository.count() == 0);
    }
}
