package com.example.yallahride.Controller;

import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Service.Interface.CarImageService;
import com.example.yallahride.Service.Interface.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarImageController.class)
public class CarImageControllerTest {
    @MockBean
    CarService carService;

    @MockBean
    CarImageService carImageService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetCarImage() throws Exception {
        CarImage carImage = new CarImage("newImage");
        carImage.setId(1L);
        carImageService.saveCarImage(carImage);

        when(carImageService.findCarImageById(1L)).thenReturn(Optional.of(carImage));

        mockMvc.perform(MockMvcRequestBuilders.get("/car-image/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andDo(print());
    }

    @Test
    public void testSaveCarImage() throws Exception {
        CarImage carImage = new CarImage("newImage2");
        carImage.setId(1L);

        when(carImageService.saveCarImage(carImage)).thenReturn(carImage);


        mockMvc.perform(MockMvcRequestBuilders.post("/car-image/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carImage)))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.imagePath").value("newImage2"))
                .andDo(print());
    }

    @Test
    public void testDeleteCarImage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/car-image/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andDo(print());

        verify(carImageService).deleteCarImageById(1L);
    }

    @Test
    public void testGetCarsImages() throws Exception {
        CarImage carImage1 = new CarImage("car/image1");
        carImage1.setId(1L);

        CarImage carImage2 = new CarImage("car/image2");
        carImage2.setId(2L);

        List<CarImage> carImages = new ArrayList<>();
        carImages.add(carImage1);
        carImages.add(carImage2);

        when(carImageService.findAllCarImages()).thenReturn(carImages);

        mockMvc.perform(MockMvcRequestBuilders.get("/car-image/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[0].imagePath").value(carImage1.getImagePath()))
                .andExpect(jsonPath("$[1].imagePath").value(carImage2.getImagePath())).andDo(print());
    }


}
