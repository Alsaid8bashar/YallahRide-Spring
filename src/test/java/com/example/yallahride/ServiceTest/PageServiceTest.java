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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

    @BeforeAll
    void setPage() {
        page = new Page();
        pageService.savePage(page);
        page = pageService.savePage(page);
    }

    @Test
    @Order(1)
    void addPage() {
        Page page = new Page();
        pageService.savePage(page);
        Assertions.assertThat(pageService.findPageById(page.getId())).isNotNull();
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
        MultipartFile multipartFile = new MockMultipartFile("image1.png", "image1.png!".getBytes());
        PageImage pageImage = new PageImage(multipartFile);
        pageImage.setPage(page);
        pageService.addImage(page.getId(), pageImage);
        Assertions.assertThat(pageService.getPageImages(page.getId())).isNotEmpty();
    }

    @Test
    @Order(4)
    void addPageVideos() {
        MultipartFile multipartFile = new MockMultipartFile("video.mp3", "video.mp3!".getBytes());
        PageVideo pageVideo = new PageVideo(multipartFile);
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

    @Test
    @Order(8)
    void deletePage() {
        Long id = page.getId();
        pageService.deletePageById(id);
        Assertions.assertThat(pageService.findAllPages().contains(page)).isFalse();
    }
}
