package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.PageImage;

import java.util.List;
import java.util.Optional;

public interface PageImageService {

    void savePageImage(PageImage car);

    Optional<PageImage> findPageImageById(Long id);

    List<PageImage> findAllPageImages();

    void deleteAllPageImages();

    void deleteImageById(Long id);
}
