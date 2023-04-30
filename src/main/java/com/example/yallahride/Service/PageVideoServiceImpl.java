package com.example.yallahride.Service;

import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Repository.PageVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageVideoServiceImpl implements PageVideoService{

    @Autowired
    PageVideoRepository pageVideoRepository;
    @Override
    public void savePageVideo(PageVideo pageVideo) {
        pageVideoRepository.save(pageVideo);
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
