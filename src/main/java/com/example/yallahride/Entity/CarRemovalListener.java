package com.example.yallahride.Entity;

import jakarta.persistence.PreRemove;

public class CarRemovalListener {


    @PreRemove
    public void removeCarImagesFromS3(Car car){
        //Logic
    }

}
