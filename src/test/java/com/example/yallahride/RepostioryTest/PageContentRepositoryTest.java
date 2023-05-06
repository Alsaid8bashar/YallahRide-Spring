package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Repository.PageContentRepository;
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
    PageContentRepository pageContentRepository;
    PageContent pageContent;

    @BeforeAll
    public void setup() {
        pageContent = pageContentRepository.save(new PageContent("VideoPath1"));
    }

    @Test
    @Order(1)
    public void testCreatePageContent() {
        PageContent PageContent = pageContentRepository.save(new PageContent("VideoPath2"));
        Assertions.assertTrue(PageContent.getId() > 0);
    }


    @Test
    @Order(2)
    public void testFindPageContentById() {
        Optional<PageContent> optionalRide = pageContentRepository.findById(pageContent.getId());
        PageContent tempRide = optionalRide.get();
        Assertions.assertEquals(tempRide.getId(), pageContent.getId());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testUpdatePageContent() {
        pageContent.setContent("UpdatedContent");
        PageContent contentUpdated = pageContentRepository.save(pageContent);
        Assertions.assertEquals(pageContent.getContent(), contentUpdated.getContent());
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void testFindAllPageContents() {
        List<PageContent> rideList = pageContentRepository.findAll();
        Assertions.assertTrue(rideList.size() > 0);
    }

    @Test
    @Order(5)
    public void testDeletePageContentByID() {
        pageContentRepository.deleteById(pageContent.getId());
        Optional<PageContent> optionalRide = pageContentRepository.findById(pageContent.getId());
        Assertions.assertTrue(!optionalRide.isPresent());
    }

    @Test
    @Order(6)
    public void testDeleteAllPageContent() {
        pageContentRepository.deleteAll();
        Assertions.assertTrue(pageContentRepository.count() == 0);
    }
}
