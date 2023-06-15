package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.Report;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.RideService;
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RideController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RideControllerTest {


    @MockBean
    RideService rideService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    Car car = new Car("Black", "Ford", "Fusion", "19-89893", 2014, new User());
    Date currentDate = new Date();
    LocalTime time = LocalTime.of(10, 30, 0);

    @Test
    public void testCreateRide() throws Exception {
        User user = new User("Hassan", "Al-Shannag","male");
        Ride ride = new Ride("Irbid", "Amman", currentDate, 5, 2.5, user, car,time,time);
        ride.setId(1L);

        when(rideService.saveRide(ride)).thenReturn(ride);

        mockMvc.perform(post("/ride/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ride)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.from").value(ride.getFrom()))
                .andExpect(jsonPath("$.to").value(ride.getTo()))
                .andExpect(jsonPath("$.seats").value(ride.getSeats()))
                .andDo(print());

    }

    @Test
    public void testUpdateRide() throws Exception {
        User user = new User("Hassan", "Al-Shannag","male");
        Ride ride = new Ride("Irbid", "Amman", currentDate, 5, 2.5, user, car,time,time);
        ride.setId(1L);

        when(rideService.updateRide(ride)).thenReturn(ride);
        mockMvc.perform(MockMvcRequestBuilders.put("/ride/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ride)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.from").value(ride.getFrom()))
                .andExpect(jsonPath("$.to").value(ride.getTo()))
                .andExpect(jsonPath("$.seats").value(ride.getSeats()))
                .andDo(print());

    }

    @Test
    public void testFindRideById() throws Exception {
        Long rideId = 1L;
        User user = new User("Hassan", "Al-Shannag","male");
        Ride ride = new Ride("Irbid", "Amman", currentDate, 5, 2.5, user, car,time,time);
        ride.setId(rideId);

        when(rideService.findRideById(rideId)).thenReturn(ride);

        mockMvc.perform(MockMvcRequestBuilders.get("/ride/{id}", rideId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.from").value(ride.getFrom()))
                .andExpect(jsonPath("$.to").value(ride.getTo()))
                .andExpect(jsonPath("$.seats").value(ride.getSeats()))
                .andDo(print());
    }

    @Test
    public void testFindAllRides() throws Exception {
        java.util.List<Ride> rides = Arrays.asList(
                new Ride("Amman", "irbid",currentDate, 4, 2.5, new User(),car,time,time),
                new Ride("Amman", "irbid",currentDate, 4, 2.5, new User(),car,time,time)
        );

        when(rideService.findAllRides()).thenReturn(rides);

        mockMvc.perform(MockMvcRequestBuilders.get("/ride/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].from").value("Amman"))
                .andExpect(jsonPath("$[1].from").value("Salt"))
                .andDo(print());

    }

    @Test
    public void testDeleteRideById() throws Exception {
        Long rideId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/ride/delete/{id}", rideId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
        verify(rideService, times(1)).deleteRideById(rideId);
    }

    @Test
    public void testDeleteAllRides() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/ride/delete/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());


        verify(rideService, times(1)).deleteAllRides();
    }

    @Test
    public void testFindAllRideReports() throws Exception {
        User user = new User("Hassan", "Al-Shannag","male");
        Ride ride = new Ride("Amman", "irbid",currentDate, 4, 2.5, user,car,time,time);

        Set<Report> reports = new HashSet<>();

        reports.add(new Report("Amman ride", "report 1"));
        reports.add(new Report("Amman trip", "report 2"));
        ride.setReports(reports);

        when(rideService.findRideReports(ride)).thenReturn(reports);

        mockMvc.perform(MockMvcRequestBuilders.get("/ride/reports").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ride)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }

}
