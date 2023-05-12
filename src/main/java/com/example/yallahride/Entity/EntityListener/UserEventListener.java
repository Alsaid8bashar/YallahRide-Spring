package com.example.yallahride.Entity.EntityListener;

import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {
    @Autowired
    FileService fileService;


    public void removeUserImage(User user) {
        fileService.deleteFile(user.getImagePath());
    }
}
