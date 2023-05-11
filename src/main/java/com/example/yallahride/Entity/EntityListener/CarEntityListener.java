package com.example.yallahride.Entity.EntityListener;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Service.Interface.FileService;
import jakarta.persistence.PreRemove;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CarEntityListener {

    @Autowired
    FileService fileService;


    @PreRemove
    public void removeCarImage(Car car) {
        java.util.List<String> keys = new ArrayList<>();
        for (CarImage k : car.getCarImages()) {
            keys.add(k.getImagePath());
        }
        fileService.deleteFiles(keys);
    }
}
