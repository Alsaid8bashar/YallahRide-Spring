package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Service.Interface.PageImageService;
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
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        Page page = new Page();
        page.setId(1L);
        Long pageImageId = 1L;
        MockMultipartFile file = new MockMultipartFile(
                "multipartFile",
                "image.png",
                MediaType.IMAGE_JPEG_VALUE,
                "image".getBytes());
        PageImage  image = new PageImage(file);
        image.setPage(page);
        image.setMultipartFile(file);
        image.setId(pageImageId);


        when(pageImageService.savePageImage(image)).thenReturn(image);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/page-image/create")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(String.valueOf(image)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testSavePageImage() throws Exception {
        Long pageImageId = 1L;
        PageImage pageImage = new PageImage();
        MockMultipartFile file = new MockMultipartFile(
                "multipartFile",
                "image.png",
                MediaType.IMAGE_JPEG_VALUE,
                "image".getBytes());

        pageImage.setId(pageImageId);
        Page page = new Page();
        page.setId(1L);
        pageImage.setPage(page);

        when(pageImageService.savePageImage(pageImage)).thenReturn(pageImage);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/page-image/create")
                        .file(file)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pageImage)))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    public void testDeletePageImage() throws Exception {
        Long pageImageId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/page-image/delete/{id}", pageImageId)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent()).andDo(print());

        verify(pageImageService, times(1)).deleteImageById(pageImageId);
    }

    @Test
    public void testGetPageImages() throws Exception {
        java.util.List<PageImage> pageImages = Arrays.asList(
                new PageImage(),
                new PageImage());

        when(pageImageService.findAllPageImages()).thenReturn(pageImages);
        mockMvc.perform(MockMvcRequestBuilders.get("/page-image/all")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].imagePath").value("image1.jpg"))
//                .andExpect(jsonPath("$[1].imagePath").value("image2.jpg"))
                .andDo(print());
    }

    @Test
    public void testDeletePagesImages() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/page-image/delete/all")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent()).andDo(print());

        verify(pageImageService, times(1)).deleteAllPageImages();
    }

}
