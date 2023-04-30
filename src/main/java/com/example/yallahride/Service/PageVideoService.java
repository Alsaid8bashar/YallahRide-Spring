package com.example.yallahride.Service;

import com.example.yallahride.Entity.PageVideo;

import java.util.List;
import java.util.Optional;

public interface PageVideoService {
    void savePageVideo(PageVideo pageVideo);

    Optional<PageVideo> findPageVideoById(Long id);

    List<PageVideo> findAllPageVideos();

    void deleteAllPageVideos();

    void deletePageVideoById(Long id);

    long getNumberOfPageVideo();
}
