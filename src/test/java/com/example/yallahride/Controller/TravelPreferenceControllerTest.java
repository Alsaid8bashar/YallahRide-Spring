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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void updateTravelPreferenceByIdTest() throws Exception {
        TravelPreference travelPreference = new TravelPreference("TEST");
        travelPreference.setId(1L);
        travelPreference.setDescription("TEST_UPDATED");
        when(travelPreferenceService.findTravelPreferenceById(1L)).thenReturn(travelPreference);
        when(travelPreferenceService.updateTravelPreference(travelPreference)).thenReturn(travelPreference);
        mockMvc.perform(put("/travel-preference/{id}/update", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(travelPreference)))
                .andExpect(jsonPath("$.id").value(travelPreference.getId()))
                .andExpect(jsonPath("$.description").value(travelPreference.getDescription()))
                .andExpect(status().isOk())
                .andDo(print());
    }



    @Test
    void findTravelPreferenceByIdTest() throws Exception {
        TravelPreference travelPreference = new TravelPreference("TEST");
        travelPreference.setId(1L);

        when(travelPreferenceService.findTravelPreferenceById(1L)).thenReturn(travelPreference);

        mockMvc.perform(get("/travel-preference/find/{id}", 1L))

                .andExpect(jsonPath("$.id").value(travelPreference.getId()))
                .andExpect(jsonPath("$.description").value(travelPreference.getDescription()))
                .andExpect(status().isOk())
                .andDo(print());
    }



    @Test
    void findAllTravelPreferencesTest() throws Exception {
        List<TravelPreference> travelPreferenceList = new ArrayList<>();
        for(int x = 0; x < 5 ;x++)
        {
            travelPreferenceList.add(new TravelPreference("Travel preference.." + (x+1)));
            travelPreferenceList.get(x).setId((long) (x+1));
        }

        when(travelPreferenceService.findAllTravelPreferences()).thenReturn(travelPreferenceList);

        mockMvc.perform(get("/travel-preference/all", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(travelPreferenceList.size()))
                .andDo(print());
    }

    @Test
    void deleteAllTravelPreferencesTest() throws Exception {
        doNothing().when(travelPreferenceService).deleteAllTravelPreferences();
        mockMvc.perform(delete("/travel-preference/delete_all"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void deleteTravelPreferenceById() throws Exception {
        Long id = 1L;
        doNothing().when(travelPreferenceService).deleteTravelPreferenceById(id);
        mockMvc.perform(delete("/travel-preference/delete/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void travelPreferenceCount() throws Exception {
        List<TravelPreference> travelPreferenceList = new ArrayList<>();
        for(int x = 0; x < 5 ;x++)
        {
            travelPreferenceList.add(new TravelPreference("Travel preference.." + (x+1)));
            travelPreferenceList.get(x).setId((long) (x+1));
        }

        when(travelPreferenceService.getNumberOfTravelPreference()).thenReturn((long) travelPreferenceList.size());
        mockMvc.perform(get("/travel-preference/statistics"))
                .andExpect(jsonPath("$").value(travelPreferenceList.size()))
                .andExpect(status().isOk())
                .andDo(print());

    }

}