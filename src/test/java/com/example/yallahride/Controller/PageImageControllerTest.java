package com.example.yallahride.Controller;

import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Service.Interface.PageImageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

@WebMvcTest(PageImageController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PageImageControllerTest {

    @MockBean
    PageImageService pageImageService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetPageImage() throws Exception {
        Long pageImageId = 1L;
        PageImage pageImage = new PageImage( "image.jpg");
        pageImage.setId(pageImageId);


        when(pageImageService.findPageImageById(pageImageId)).thenReturn(pageImage);

        mockMvc.perform(MockMvcRequestBuilders.get("/page-image/{id}", pageImageId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pageImageId))
                .andExpect(jsonPath("$.imagePath").value("image.jpg")).andDo(print());
    }

    @Test
    public void testSavePageImage() throws Exception {
        Long pageImageId = 1L;
        PageImage pageImage = new PageImage( "image.jpg");
        pageImage.setId(pageImageId);


        when(pageImageService.savePageImage(pageImage)).thenReturn(pageImage);

        mockMvc.perform(MockMvcRequestBuilders.post("/page-image/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pageImage)))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1L))
//                .andExpect(jsonPath("$.imagePath").value("image.jpg"))
                .andDo(print());
    }

    @Test
    public void testDeletePageImage() throws Exception {
        Long pageImageId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/page-image/delete/{id}", pageImageId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andDo(print());

        verify(pageImageService, times(1)).deleteImageById(pageImageId);
    }

    @Test
    public void testGetPageImages() throws Exception {
        java.util.List<PageImage> pageImages = Arrays.asList(
                new PageImage( "image1.jpg"),
                new PageImage( "image2.jpg"));

        when(pageImageService.findAllPageImages()).thenReturn(pageImages);
        mockMvc.perform(MockMvcRequestBuilders.get("/page-image/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].imagePath").value("image1.jpg"))
                .andExpect(jsonPath("$[1].imagePath").value("image2.jpg")).andDo(print());
    }

    @Test
    public void testDeletePagesImages() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/page-image/delete/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andDo(print());

        verify(pageImageService, times(1)).deleteAllPageImages();
    }

}
