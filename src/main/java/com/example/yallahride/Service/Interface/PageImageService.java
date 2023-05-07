package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.PageImage;

import java.util.List;
import java.util.Optional;

public interface PageImageService {

    PageImage savePageImage(PageImage pageImage);

    PageImage findPageImageById(Long id);

    List<PageImage> findAllPageImages();
    PageImage updatePageImage(PageImage pageImage);

    void deleteAllPageImages();

    void deleteImageById(Long id);
}
