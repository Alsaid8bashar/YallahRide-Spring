package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.PageVideo;

import java.util.List;
import java.util.Optional;

public interface PageVideoService {
    PageVideo savePageVideo(PageVideo pageVideo);

    Optional<PageVideo> findPageVideoById(Long id);

    List<PageVideo> findAllPageVideos();
    PageVideo updatePageVideo(PageVideo pageVideo);

    void deleteAllPageVideos();

    void deletePageVideoById(Long id);

    long getNumberOfPageVideo();
}
