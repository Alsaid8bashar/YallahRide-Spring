package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Repository.PageImagesRepository;
import com.example.yallahride.Service.Interface.PageImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class PageImageServiceImpl implements PageImageService {
    final PageImagesRepository pageImagesRepository;

    public PageImageServiceImpl(PageImagesRepository pageImagesRepository) {
        this.pageImagesRepository = pageImagesRepository;
    }

    @Override
    public PageImage savePageImage(PageImage pageImage) {
        return pageImagesRepository.save(pageImage);
    }

    @Override
    public Optional<PageImage> findPageImageById(Long id) {
        return pageImagesRepository.findById(id);
    }

    @Override
    public List<PageImage> findAllPageImages() {
        return pageImagesRepository.findAll();
    }

    @Override
    public PageImage updatePageImage(PageImage pageImage) {
        return pageImagesRepository.save(pageImage);
    }

    @Override
    public void deleteAllPageImages() {
        pageImagesRepository.deleteAll();
    }

    @Override
    public void deleteImageById(Long id) {
        pageImagesRepository.deleteById(id);
    }
}
