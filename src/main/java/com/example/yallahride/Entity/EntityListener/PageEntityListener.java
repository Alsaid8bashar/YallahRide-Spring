package com.example.yallahride.Entity.EntityListener;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Service.Interface.FileService;
import jakarta.persistence.PreRemove;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PageEntityListener {

    FileService fileService;



    @PreRemove
    public void removeAllPageImages(Page page){
        List<PageImage> pageImagesList = new ArrayList<>(page.getPageImageSet());
        List<String> keys = new ArrayList<>();

        for(PageImage pageImage : pageImagesList){
            keys.add(pageImage.getImagePath());
        }

        fileService.deleteFiles(keys);
    }


    @PreRemove
    public void removeAllPageVideos(Page page){
        List<PageVideo> pageVideoList = new ArrayList<>(page.getPageVideoSet());
        List<String> keys = new ArrayList<>();

        for(PageVideo pageVideo : pageVideoList){
            keys.add(pageVideo.getVideoPath());
        }

        fileService.deleteFiles(keys);
    }

}
