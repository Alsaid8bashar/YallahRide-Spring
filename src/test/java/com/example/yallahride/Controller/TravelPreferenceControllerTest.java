package com.example.yallahride.Controller;

import com.example.yallahride.Entity.TravelPreference;
import com.example.yallahride.Service.Interface.TravelPreferenceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TravelPreferenceController.class)
class TravelPreferenceControllerTest {

    @MockBean
    TravelPreferenceService travelPreferenceService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createTravelPreferenceTest() throws Exception {
        TravelPreference travelPreference = new TravelPreference("TEST");
        travelPreference.setId(1L);

        when(travelPreferenceService.saveTravelPreference(travelPreference)).thenReturn(travelPreference);

        mockMvc.perform(post("/travel-preference/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(travelPreference)))
                .andExpect(jsonPath("$.id").value(travelPreference.getId()))
                .andExpect(jsonPath("$.description").value(travelPreference.getDescription()))

                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void updateTravelPreferenceTest() throws Exception {
        TravelPreference travelPreference = new TravelPreference("TEST");
        travelPreference.setId(1L);
        travelPreference.setDescription("TEST_UPDATED");

        when(travelPreferenceService.updateTravelPreference(travelPreference)).thenReturn(travelPreference);
        mockMvc.perform(put("/travel-preference/{id}/update", travelPreference.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(travelPreference)))
                .andExpect(jsonPath("$.id").value(travelPreference.getId()))
                .andExpect(jsonPath("$.description").value(travelPreference.getDescription()))
                .andExpect(status().isOk())
                .andDo(print());
    }

}