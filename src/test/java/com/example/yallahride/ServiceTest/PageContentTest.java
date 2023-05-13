package com.example.yallahride.ServiceTest;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Service.Interface.PageContentService;
import com.example.yallahride.Service.Interface.PageService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PageContentTest {
    @Autowired
    PageService pageService;
    @Autowired
    private PageContentService pageContentService;
    PageContent pageContent;
    Page page;

    @BeforeAll
    void setPage() {
        page = pageService.savePage(new Page());
    }

    @Test
    @Order(1)
    public void contextLoadTest() {
        org.assertj.core.api.Assertions.assertThat(pageService != null);
        org.assertj.core.api.Assertions.assertThat(pageContentService != null);
    }

    @Test
    @Order(2)
    public void savePageContent() {
        pageContent = new PageContent("Content!");
        pageContent.setPage(page);
        pageContent = pageContentService.savePageContent(pageContent);
        Assertions.assertTrue(pageContent.getId() > 0);
    }

    @Test
    @Order(3)
    public void findPageContentById() {
        Assertions.assertTrue(pageContentService.findPageContentById(pageContent.getId()).getId() > 0);
    }

    @Test
    @Order(4)
    public void findAllPageContents() {
        PageContent pageContent1 = new PageContent("Content 1");
        pageContent1.setPage(page);
        pageContentService.savePageContent(pageContent1);
        Assertions.assertTrue(pageContentService.findAllPageContents().size() > 0);
    }


    @Test
    @Order(5)
    public void deletePageContentById() {
        pageContentService.deletePageContentById(pageContent.getId());
        Assertions.assertThrowsExactly(EntityNotFoundException.class, () ->
                pageContentService.findPageContentById(pageContent.getId()));
    }

    @Test
    @Order(6)
    public void should_not_remove_parent_when_child_removed(){
        Assertions.assertTrue(pageService.findPageById(page.getId()).getId() > 0);
    }

    @Test
    @Order(7)
    public void deleteAllPageContents() {
        pageContentService.deleteAllPageContents();
        Assertions.assertEquals(0, pageContentService.findAllPageContents().size());
    }

    @AfterAll
    public void cleanup(){
        pageService.deletePageById(page.getId());
    }
}
