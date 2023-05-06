package com.example.yallahride.Controller;

import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Service.Interface.PageContentService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PageContentController.class)
public class PageContentControllerTest {
    @MockBean
    PageContentService pageContentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetPageContent() throws Exception {
        Long pageContentId = 1L;
        PageContent pageContent = new PageContent("Content 1");
        pageContent.setId(pageContentId);


        when(pageContentService.findPageContentById(pageContentId)).thenReturn(Optional.of(pageContent));

        mockMvc.perform(MockMvcRequestBuilders.get("/page-content/{id}", pageContentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pageContentId))
                .andExpect(jsonPath("$.content").value("Content 1"));
    }

    @Test
    public void testSavePageContent() throws Exception {
        Long pageContentId = 1L;
        PageContent pageContent = new PageContent("Content 1");
        pageContent.setId(pageContentId);

        when(pageContentService.savePageContent(pageContent)).thenReturn(pageContent);

        mockMvc.perform(MockMvcRequestBuilders.post("/page-content/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pageContent)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.content").value("Content 1"));
    }

    @Test
    public void testDeletePageContent() throws Exception {
        Long pageContentId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/page-content/delete/{id}", pageContentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(pageContentService, times(1)).deletePageContentById(pageContentId);
    }

    @Test
    public void testGetPageContents() throws Exception {
        java.util.List<PageContent> pageContents = Arrays.asList(
                new PageContent("Content 1"),
                new PageContent("Content 2")
        );
        when(pageContentService.findAllPageContents()).thenReturn(pageContents);

        mockMvc.perform(MockMvcRequestBuilders.get("/page-content/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Content 1"))
                .andExpect(jsonPath("$[1].content").value("Content 2"));
    }

    @Test
    public void testDeletePagesContents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/page-content/delete/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(pageContentService, times(1)).deleteAllPageContents();
    }
}
