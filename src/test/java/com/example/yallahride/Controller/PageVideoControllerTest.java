package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Service.Interface.PageVideoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PageVideoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PageVideoControllerTest {
    @MockBean
    PageVideoService pageVideoService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetPageVideo() throws Exception {
        Long pageVideoId = 1L;
        PageVideo pageVideo = new PageVideo();
        pageVideo.setId(pageVideoId);

        when(pageVideoService.findPageVideoById(pageVideoId)).thenReturn(pageVideo);
        mockMvc.perform(MockMvcRequestBuilders.get("/page-video/{id}", pageVideoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print()).andDo(print());
//                .andExpect(jsonPath("$.id").value(pageVideoId))
//                .andExpect(jsonPath("$.videoPath").value("video.mp4"));
    }

    @Test
    public void testSavePageVideo() throws Exception {
        Page page = new Page();
        page.setId(1L);
        Long pageImageId = 1L;
        MockMultipartFile file = new MockMultipartFile(
                "multipartFile",
                "image.png",
                MediaType.IMAGE_JPEG_VALUE,
                "image".getBytes());
        PageVideo video = new PageVideo(file);


        video.setPage(page);
        video.setMultipartFile(file);
        video.setId(pageImageId);


        when(pageVideoService.savePageVideo(video)).thenReturn(video);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/page-video/create")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(String.valueOf(video)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testDeletePageVideo() throws Exception {
        Long pageVideoId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/page-video/delete/{id}", pageVideoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());


        verify(pageVideoService, times(1)).deletePageVideoById(pageVideoId);
    }

    @Test
    public void testGetPageVideos() throws Exception {
        java.util.List<PageVideo> pageVideos = Arrays.asList(
                new PageVideo(),
                new PageVideo());
        when(pageVideoService.findAllPageVideos()).thenReturn(pageVideos);

        mockMvc.perform(MockMvcRequestBuilders.get("/page-video/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].videoPath").value("video1.mp4"))
//                .andExpect(jsonPath("$[1].videoPath").value("video2.mp4"))
                .andDo(print());

    }

    @Test
    public void testDeletePagesVideos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/page-video/delete/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());


        verify(pageVideoService, times(1)).deleteAllPageVideos();
    }
}
