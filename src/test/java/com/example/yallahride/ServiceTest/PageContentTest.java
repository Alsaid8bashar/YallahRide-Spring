package com.example.yallahride.ServiceTest;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Repository.PageContentRepository;
import com.example.yallahride.Service.Interface.PageContentService;
import com.example.yallahride.Service.Interface.PageService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PageContentTest {
    @Autowired
    PageContentRepository pageContentRepository;

    @Autowired
    PageService pageService;
    Page page;
    @Autowired
    private PageContentService pageContentService;

    @BeforeAll
    void setPage() {
        page = new Page();
        pageService.savePage(page);
        page = pageService.savePage(page);
    }


    @Test
    @Order(1)
    public void savePageContent() {
        PageContent pageContent = new PageContent("Content");
        pageContent.setPage(page);


        pageContentService.savePageContent(pageContent);
        Assertions.assertTrue(pageContentService.findAllPageContents().contains(pageContent));
    }

    @Test
    @Order(2)
    public void findPageContentById() {
        PageContent pageContent1 = new PageContent("Content 1");
        PageContent pageContent2 = new PageContent("Content 2");
        pageContent1.setPage(page);
        pageContent2.setPage(page);


        pageContentService.savePageContent(pageContent1);
        pageContentService.savePageContent(pageContent2);

        PageContent result1 = pageContentService.findPageContentById(pageContent1.getId());
        PageContent result2 = pageContentService.findPageContentById(pageContent2.getId());

        Assertions.assertEquals(pageContent1, result1);
        Assertions.assertEquals(pageContent2, result2);
    }

    @Test
    @Order(3)
    public void findAllPageContents() {
        PageContent pageContent1 = new PageContent("Content 1");
        PageContent pageContent2 = new PageContent("Content 2");
        pageContent1.setPage(page);
        pageContent2.setPage(page);

        pageContentService.savePageContent(pageContent1);
        pageContentService.savePageContent(pageContent2);

        List<PageContent> expected = Arrays.asList(pageContent1, pageContent2);
        List<PageContent> result = pageContentService.findAllPageContents();

        Assertions.assertTrue(result.size() > 0);

    }


    @Test
    @Order(4)
    public void deletePageContentById() {
        PageContent pageContent1 = new PageContent("Content 1");
        PageContent pageContent2 = new PageContent("Content 2");
        pageContent1.setPage(page);
        pageContent2.setPage(page);


        pageContentService.savePageContent(pageContent1);
        pageContentService.savePageContent(pageContent2);
        pageContentService.deletePageContentById(pageContent1.getId());
        Assertions.assertFalse(pageContentService.findAllPageContents().contains(pageContent1));
        Assertions.assertTrue(pageContentService.findAllPageContents().contains(pageContent2));
    }

    @Test
    @Order(5)
    public void deleteAllPageContents() {
        PageContent pageContent1 = new PageContent("Content 1");
        PageContent pageContent2 = new PageContent("Content 2");
        pageContent1.setPage(page);
        pageContent2.setPage(page);


        pageContentService.savePageContent(pageContent1);
        pageContentService.savePageContent(pageContent2);

        pageContentService.deleteAllPageContents();


        Assertions.assertEquals(0, pageContentService.findAllPageContents().size());
    }
}
