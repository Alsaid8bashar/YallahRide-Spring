package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Repository.PageVideoRepository;
import com.example.yallahride.Service.Interface.PageVideoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageVideoServiceImpl implements PageVideoService {

    final PageVideoRepository pageVideoRepository;

    public PageVideoServiceImpl(PageVideoRepository pageVideoRepository) {
        this.pageVideoRepository = pageVideoRepository;
    }

    @Override

    public PageVideo savePageVideo(PageVideo pageVideo) {
        return pageVideoRepository.save(pageVideo);
    }

    @Override
    public Optional<PageVideo> findPageVideoById(Long id) {
        return pageVideoRepository.findById(id);
    }

    @Override
    public List<PageVideo> findAllPageVideos() {
        return pageVideoRepository.findAll();
    }

    @Override
    public PageVideo updatePageVideo(PageVideo pageVideo) {
        return pageVideoRepository.save(pageVideo);
    }

    @Override
    public void deleteAllPageVideos() {
        pageVideoRepository.deleteAll();
    }

    @Override
    public void deletePageVideoById(Long id) {
        pageVideoRepository.deleteById(id);
    }

    @Override
    public long getNumberOfPageVideo() {
        return pageVideoRepository.count();
    }
}
