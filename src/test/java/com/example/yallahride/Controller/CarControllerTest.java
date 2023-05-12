package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CarControllerTest {

    @MockBean
    CarService carService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetCar() throws Exception {
        Car car = new Car("Black", "Ford", "Fusion", "10-100", 2016, new User());
        car.setId(1L);

        when(carService.findCarById(1L)).thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.get("/car/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(car.getId()))
                .andExpect(jsonPath("$.color").value(car.getColor())).andDo(print());
    }

    @Test
    void createCarTest() throws Exception {
        Car car = new Car("Black", "Ford", "Fusion", "10-100", 2016, new User());
        car.setId(1L);
        when(carService.saveCar(car)).thenReturn(car);

        mockMvc.perform(post("/car/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car)))
                .andExpect(jsonPath("$.id").value(car.getId()))
                .andExpect(jsonPath("$.color").value(car.getColor()))
                .andExpect(status().isCreated())
                .andDo(print());
    }


    @Test
    public void testGetCars() throws Exception {
        Car car = new Car("Black", "Ford", "Fusion", "10-100", 2016, new User());
        car.setId(1L);

        when(carService.findAllCars()).thenReturn(Collections.singletonList(car));

        mockMvc.perform(MockMvcRequestBuilders.get("/car/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].color").value(car.getColor()))
                .andDo(print());
    }

    @Test
    public void testAddCarImage() throws Exception {
        Car car = new Car("Black", "Ford", "Fusion", "10-100", 2016, new User());
        car.setId(1L);

        CarImage carImage = new CarImage("newImage");
        carImage.setCar(car);
        carImage.setId(1L);
        when(carService.addCarImage(1L, carImage)).thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.post("/car/add/image")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carImage)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testGetCarImages() throws Exception {
        CarImage carImage1 = new CarImage();
        carImage1.setId(1L);

        CarImage carImage2 = new CarImage();
        carImage2.setId(2L);

        java.util.List<CarImage> carImages = new ArrayList<>();
        carImages.add(carImage1);
        carImages.add(carImage2);

        when(carService.getAllCarImages(1L)).thenReturn(carImages);

        mockMvc.perform(MockMvcRequestBuilders.get("/car/all/images")
                        .param("carId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L)).andDo(print());
    }

    @Test
    public void testDeleteCarImage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/car/delete-image/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andDo(print());
        verify(carService).deleteCarImage(1L);
    }

    @Test
    public void testDeleteCars() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/car/delete/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andDo(print());

        verify(carService).deleteAllCars();
    }

    @Test
    public void testGetNumberOfCars() throws Exception {
        long numberOfCars = 10L;

        when(carService.getNumberOfCars()).thenReturn(numberOfCars);

        mockMvc.perform(MockMvcRequestBuilders.get("/car/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(numberOfCars)).andDo(print());
    }

}
