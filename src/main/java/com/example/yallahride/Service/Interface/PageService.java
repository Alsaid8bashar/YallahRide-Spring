package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Entity.PageVideo;

import java.util.List;
import java.util.Optional;

public interface PageService {

    Page savePage(Page page);

    Optional<Page> findPageById(Long id);

    List<Page> findAllPages();

    void deleteAllPages();

    void deletePageById(Long id);

    long getNumberOfPages();
    List<PageImage>getAllPageImagesByPage(Page page);
    List<PageVideo>getAllPageVideosByPage(Page page);
    List<PageContent>getAllPageContentsByPage(Page page);
}
