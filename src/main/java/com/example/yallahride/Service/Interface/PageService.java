package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Entity.PageVideo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PageService {

    Page savePage(Page page);

    Page findPageById(Long id);

    List<Page> findAllPages();

    void deleteAllPages();

    void deletePageById(Long id);

    long getNumberOfPages();

    Page addContent(Long pageId, PageContent pageContent);

    Page addImage(Long pageId, PageImage pageImage);

    Page addVideo(Long pageId, PageVideo pageVideo);

    Collection<PageContent> getPageContents(Long pageId);

    Collection<PageImage> getPageImages(Long pageId);

    Collection<PageVideo> getPageVideos(Long pageId);


}
