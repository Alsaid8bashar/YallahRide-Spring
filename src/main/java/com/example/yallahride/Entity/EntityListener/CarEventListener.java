package com.example.yallahride.Entity.EntityListener;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Service.Interface.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CarEventListener {
    @Autowired
    FileService fileService;

    public void onCarRemoval(Car car) {
        java.util.List<String> keys = new ArrayList<>();
        for (CarImage k : car.getCarImages()) {
            keys.add(k.getImagePath());
        }
//        fileService.deleteFiles(keys);
    }
}