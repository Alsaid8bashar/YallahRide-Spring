package com.example.yallahride.Entity.EntityListener;

import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.FileService;
import jakarta.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class UserEntityListener {
    @Autowired
    FileService fileService;


    @PreRemove
    public void removeUserImage(User user) {
        fileService.deleteFile(user.getImagePath());
    }
}
