package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Rate;
import com.example.yallahride.Service.Interface.RateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RateController.class)
public class RateControllerTest {
    @MockBean
    RateService rateService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveRate() throws Exception {
        Rate rate = new Rate(1.0);

        when(rateService.saveRate(rate)).thenReturn(rate);
        mockMvc.perform(MockMvcRequestBuilders.post("/rate/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rate)))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.rate").value(rate.getRate()));
                .andDo(print());
    }

    @Test
    public void testDeleteRate() throws Exception {
        Long rateId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/rate/delete/{id}", rateId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andDo(print());
        verify(rateService, times(1)).deleteRateById(rateId);
    }

    @Test
    public void testGetRates() throws Exception {
        java.util.List<Rate> rates = Arrays.asList(
                new Rate(5.0),
                new Rate(3.0)
        );
        when(rateService.findAllRates()).thenReturn(rates);

        mockMvc.perform(MockMvcRequestBuilders.get("/rate/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rate").value(5.0))
                .andExpect(jsonPath("$[1].rate").value(3.0))
                .andDo(print());

    }
    @Test
    public void testGetUserRates() throws Exception {
        Long userId = 1L;

        java.util.List<Rate> rates = Arrays.asList(
                new Rate(5.0),
                new Rate(3.0)
        );
        when(rateService.findUserRates(userId)).thenReturn(rates);

        mockMvc.perform(MockMvcRequestBuilders.get("/rate/user-rates/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rate").value(5.0))
                .andExpect(jsonPath("$[1].rate").value(3.0))
                .andDo(print());
    }

    @Test
    public void testGetUserRate() throws Exception {
        Long userId = 1L;
        double userRate = 4.5;
        when(rateService.getUserRateByUserId(userId)).thenReturn(userRate);
        mockMvc.perform(MockMvcRequestBuilders.get("/rate/user-rate/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(userRate));
    }
    @Test
    public void testDeleteAllRates() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/rate/delete/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(rateService, times(1)).deleteAllRates();
    }

    @Test
    public void testGetNumberOfRate() throws Exception {
        long numberOfRate = 10L;
        when(rateService.getNumberOfRate()).thenReturn(numberOfRate);

        mockMvc.perform(MockMvcRequestBuilders.get("/rate/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(numberOfRate));
    }
}
