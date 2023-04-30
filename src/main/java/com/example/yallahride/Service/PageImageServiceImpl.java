package com.example.yallahride.Service;

import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Repository.PageImagesRepository;
import com.example.yallahride.Repository.PageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageImageServiceImpl implements PageImageService {
    final PageImagesRepository pageImagesRepository;
    private final PageRepository pageRepository;

    public PageImageServiceImpl(PageImagesRepository pageImagesRepository, PageRepository pageRepository) {
        this.pageImagesRepository = pageImagesRepository;
        this.pageRepository = pageRepository;
    }

    @Override
    public void savePageImage(PageImage pageImage) {
        pageImagesRepository.save(pageImage);
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
    public void deleteAllPageImages() {
        pageRepository.deleteAll();
    }
}
