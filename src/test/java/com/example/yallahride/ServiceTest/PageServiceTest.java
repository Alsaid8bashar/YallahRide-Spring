package com.example.yallahride.ServiceTest;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Service.Interface.PageContentService;
import com.example.yallahride.Service.Interface.PageImageService;
import com.example.yallahride.Service.Interface.PageService;
import com.example.yallahride.Service.Interface.PageVideoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PageServiceTest {

    @Autowired
    PageService pageService;

    @Autowired
    PageContentService pageContentService;

    @Autowired
    PageImageService pageImageService;

    @Autowired
    PageVideoService pageVideoService;
    Page page;

    @BeforeEach
    void setPage() {
        page = pageService.findPageById(5L).get();
    }

    @Test
    @Order(1)
    void addPage() {
        Page page = new Page();
        pageService.savePage(page);
        Assertions.assertThat(pageService.findPageById(page.getId())).isNotNull();
    }

    @Test
    void deletePage() {
        Page page = new Page();
        pageService.savePage(page);
        Long id = page.getId();
        pageService.deletePageById(id);
        Assertions.assertThat(pageService.findAllPages().contains(page)).isFalse();
    }

    @Test
    @Order(2)
    void addPageContent() {
        PageContent pageContent = new PageContent();
        pageContent.setContent("Hello !");
        pageContent.setPage(page);
        pageService.addContent(page.getId(), pageContent);
        Assertions.assertThat(pageService.getPageContents(page.getId())).isNotEmpty();
    }

    @Test
    @Order(3)
    void addPageImage() {
        PageImage pageImage = new PageImage();
        pageImage.setImagePath("./newHomePage");
        pageImage.setPage(page);
        pageService.addImage(page.getId(), pageImage);
        Assertions.assertThat(pageService.getPageImages(page.getId())).isNotEmpty();
    }

    @Test
    @Order(4)
    void addPageVideos() {
        PageVideo pageVideo = new PageVideo();
        pageVideo.setVideoPath("./newHomeVideo");
        pageVideo.setPage(page);
        pageService.addVideo(page.getId(), pageVideo);
        Assertions.assertThat(pageService.getPageVideos(page.getId())).isNotEmpty();
    }

    @Test
    @Order(5)
    void getPageContents() {
        Assertions.assertThat(pageService.getPageContents(page.getId())).isNotEmpty();
    }

    @Test
    @Order(6)
    void getPageImages() {
        Assertions.assertThat(pageService.getPageImages(page.getId())).isNotEmpty();

    }

    @Test
    @Order(7)
    void getPageVideos() {
        Assertions.assertThat(pageService.getPageVideos(page.getId())).isNotEmpty();
    }

}
