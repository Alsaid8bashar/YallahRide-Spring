package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Exceptions.EntityNotFoundException;
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
    public PageImage findPageImageById(Long id) {
        return unwrapPageImage(pageImagesRepository.findById(id),id);
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

    static PageImage unwrapPageImage(Optional<PageImage> PageImage, Long id) {
        if (PageImage.isPresent()) return PageImage.get();
        else throw new EntityNotFoundException(id, PageImage.class);
    }
}
