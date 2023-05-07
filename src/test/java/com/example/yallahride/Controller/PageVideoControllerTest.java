package com.example.yallahride.Controller;

import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Service.Interface.PageVideoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PageVideoController.class)
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
        PageVideo pageVideo = new PageVideo("video.mp4");
        pageVideo.setId(pageVideoId);

        when(pageVideoService.findPageVideoById(pageVideoId)).thenReturn(pageVideo);
        mockMvc.perform(MockMvcRequestBuilders.get("/page-video/{id}", pageVideoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pageVideoId))
                .andExpect(jsonPath("$.videoPath").value("video.mp4"));
    }

    @Test
    public void testSavePageVideo() throws Exception {
        PageVideo pageVideo = new PageVideo("video.mp4");
        pageVideo.setId(1L);

        when(pageVideoService.savePageVideo(pageVideo)).thenReturn(pageVideo);

        mockMvc.perform(MockMvcRequestBuilders.post("/page-video/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pageVideo)))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1L))
//                .andExpect(jsonPath("$.videoPath").value("video.mp4"));
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
                new PageVideo("video1.mp4"),
                new PageVideo("video2.mp4")
        );
        when(pageVideoService.findAllPageVideos()).thenReturn(pageVideos);

        mockMvc.perform(MockMvcRequestBuilders.get("/page-video/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].videoPath").value("video1.mp4"))
                .andExpect(jsonPath("$[1].videoPath").value("video2.mp4"))
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
