package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.Enum.RideStatus;
import com.example.yallahride.Entity.Passenger;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.PassengerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PassengerController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PassengerControllerTest {


    @MockBean
    private PassengerService passengerService;
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetPassenger() throws Exception {
        Long id = 1L;
        Passenger passenger = new Passenger();
        passenger.setId(id);
        when(passengerService.findPassengerById(id)).thenReturn(passenger);

        mockMvc.perform(MockMvcRequestBuilders.get("/passenger/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andDo(print());
    }

    @Test
    void testSavePassenger() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "male");
        Car car = new Car("Black", "Ford", "Fusion", "19-89893", 2014, user);
        Date currentDate = new Date();
        LocalTime time = LocalTime.of(10, 30, 0);

        Ride ride = new Ride("Irbid", "Amman", currentDate, 5, 2.5, user, car, time, time, currentDate);
        Passenger passenger = new Passenger(user, ride, RideStatus.COMPLETED);
        when(passengerService.savePassenger(passenger)).thenReturn(passenger);

        mockMvc.perform(MockMvcRequestBuilders.post("/passenger/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passenger)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testDeletePassenger() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/passenger/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andDo(print());
        verify(passengerService).deletePassengerById(1L);
    }

    @Test
    public void testDeleteAllPassenger() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/passenger/delete/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(passengerService, times(1)).deleteAllPassengers();
    }

    @Test
    public void testGetUserRides() throws Exception {
        Long userId = 1L;
        List<Passenger> mockRides = new ArrayList<>();
        mockRides.add(new Passenger());
        mockRides.add(new Passenger());

        when(passengerService.findUserRides(anyLong())).thenReturn(mockRides);

        mockMvc.perform(MockMvcRequestBuilders.get("/passenger/user-rides")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", userId.toString()))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void testGetRidePassengers() throws Exception {
        Long rideId = 1L;
        List<Passenger> mockPassengers = new ArrayList<>();
        mockPassengers.add(new Passenger());
        mockPassengers.add(new Passenger());

        when(passengerService.findPassengersByRideId(anyLong())).thenReturn(mockPassengers);


        mockMvc.perform(MockMvcRequestBuilders.get("/passenger/ride-passengers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("rideId", rideId.toString()))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
