package com.example.yallahride.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PassengerController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PassengerControllerTest {


//    @Autowired
//    private PassengerService passengerService;
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetPassenger() throws Exception {
//        Long id = 1L;
//        Passenger passenger = new Passenger();
//        passenger.setId(id);
//        when(passengerService.findPassengerById(id)).thenReturn(passenger);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/passenger/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andDo(print());
    }

    @Test
    void testSavePassenger() throws Exception {
//        Passenger passenger = new Passenger();
//        when(passengerService.savePassenger(passenger)).thenReturn(passenger);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/passenger/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(passenger)))
//                .andExpect(status().isCreated())
//                .andDo(print());
    }


}
