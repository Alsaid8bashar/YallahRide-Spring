package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Service.Interface.PageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PageController.class)
public class PageControllerTest {

    @MockBean
    PageService pageService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetPage() throws Exception {
        Page page = new Page();
        page.setId(1L);

        when(pageService.findPageById(1L)).thenReturn(page);
        mockMvc.perform(MockMvcRequestBuilders.get("/page/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L)).andDo(print());
    }

    @Test
    public void testSavePage() throws Exception {
        Page page = new Page();
        page.setId(1L);

        when(pageService.savePage(page)).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.post("/page/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(page)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L)).andDo(print());

    }

    @Test
    public void testDeletePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/page/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andDo(print());
        verify(pageService).deletePageById(1L);
    }

    @Test
    public void testGetPages() throws Exception {
        Page page1 = new Page();
        page1.setId(1L);

        Page page2 = new Page();
        page2.setId(2L);

        java.util.List<Page> pages = new ArrayList<>();
        pages.add(page1);
        pages.add(page2);

        when(pageService.findAllPages()).thenReturn(pages);

        mockMvc.perform(MockMvcRequestBuilders.get("/page/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    public void testAddContent() throws Exception {
        Page page1 = new Page();
        page1.setId(1L);

        PageContent pageContent = new PageContent("content1");
        pageContent.setId(1L);
        pageContent.setPage(page1);


        when(pageService.addContent(1L, pageContent)).thenReturn(page1);

        mockMvc.perform(MockMvcRequestBuilders.post("/page/add/content")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pageContent)))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1L))
                .andDo(print());
    }

    @Test
    public void testAddImage() throws Exception {
        Page page = new Page();
        page.setId(1L);


        PageImage pageImage = new PageImage("HomeImage");
        pageImage.setId(1L);
        pageImage.setPage(page);

        when(pageService.addImage(1L, pageImage)).thenReturn(page);
        mockMvc.perform(MockMvcRequestBuilders.post("/page/add/image")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pageImage)))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1L))
                .andDo(print());
    }

    @Test
    public void testAddVideo() throws Exception {
        Page page = new Page();
        page.setId(1L);


        PageVideo pageVideo = new PageVideo("HomeVideo");
        pageVideo.setId(1L);
        pageVideo.setPage(page);

        when(pageService.addVideo(1L, pageVideo)).thenReturn(page);
        mockMvc.perform(MockMvcRequestBuilders.post("/page/add/video")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pageVideo)))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1L))
                .andDo(print());
    }



    @Test
    public void testGetPageContents() throws Exception {
        Long pageId = 1L;

        Collection<PageContent> pageContents = Arrays.asList(
                new PageContent("Content 1"),
                new PageContent("Content 2")
        );
        when(pageService.getPageContents(pageId)).thenReturn(pageContents);

        mockMvc.perform(MockMvcRequestBuilders.get("/page/contents")
                        .param("pageId", String.valueOf(pageId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Content 1"))
                .andExpect(jsonPath("$[1].content").value("Content 2")).andDo(print());
    }



    @Test
    public void testGetPageImages() throws Exception {
        Long pageId = 1L;

        Collection<PageImage> pageImages = Arrays.asList(
                new PageImage("image1.jpg"),
                new PageImage("image2.jpg")
        );
        when(pageService.getPageImages(pageId)).thenReturn(pageImages);

        mockMvc.perform(MockMvcRequestBuilders.get("/page/images")
                        .param("pageId", String.valueOf(pageId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].imagePath").value("image1.jpg"))
                .andExpect(jsonPath("$[1].imagePath").value("image2.jpg")).andDo(print());
    }

    @Test
    public void testGetPageVideos() throws Exception {
        Long pageId = 1L;
        Collection<PageVideo> pageVideos = Arrays.asList(
                new PageVideo("video1.mp4"),
                new PageVideo("video2.mp4")
        );
        when(pageService.getPageVideos(pageId)).thenReturn(pageVideos);

        mockMvc.perform(MockMvcRequestBuilders.get("/page/videos")
                        .param("pageId", String.valueOf(pageId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].videoPath").value("video1.mp4"))
                .andExpect(jsonPath("$[1].videoPath").value("video2.mp4")).andDo(print());
    }
    @Test
    public void testGetNumberOfPages() throws Exception {
        Long numberOfPages = 10L;
        when(pageService.getNumberOfPages()).thenReturn(numberOfPages);

        mockMvc.perform(MockMvcRequestBuilders.get("/page/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(numberOfPages));
    }


}
