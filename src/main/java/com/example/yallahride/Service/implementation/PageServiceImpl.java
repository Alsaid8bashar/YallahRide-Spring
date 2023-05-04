package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Repository.PageContentRepository;
import com.example.yallahride.Repository.PageImagesRepository;
import com.example.yallahride.Repository.PageRepository;
import com.example.yallahride.Repository.PageVideoRepository;
import com.example.yallahride.Service.Interface.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    @Autowired
    PageImagesRepository pageImagesRepository;
    @Autowired
    PageContentRepository pageContentRepository;
    @Autowired
    PageVideoRepository pageVideoRepository;

    public PageServiceImpl(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }


    @Override
    public Page savePage(Page page) {
        return pageRepository.save(page);
    }

    @Override
    public Optional<Page> findPageById(Long id) {
        return pageRepository.findById(id);
    }

    @Override
    public List<Page> findAllPages() {
        return pageRepository.findAll();
    }

    @Override
    public void deleteAllPages() {
        pageRepository.deleteAll();
    }

    @Override
    public void deletePageById(Long id) {
        pageRepository.deleteById(id);
    }

    @Override
    public long getNumberOfPages() {
        return pageRepository.count();
    }


    @Override
    public void addContent(Long pageId, PageContent pageContent) {
        Page page = findPageById(pageId).get();
        page.addContent(pageContent);
        savePage(page);
    }

    @Transactional
    @Override
    public void addImage(Long pageId, PageImage pageImage) {
        Page page = findPageById(pageId).get();
        page.addImage(pageImage);
        savePage(page);
    }

    @Transactional
    @Override
    public void addVideo(Long pageId, PageVideo pageVideo) {
        Page page = findPageById(pageId).get();
        page.addVideo(pageVideo);
        savePage(page);
    }

    @Transactional
    @Override
    public Collection<PageContent> getPageContents(Long pageId) {
        return findPageById(pageId).get().getPageContentSet();
    }

    @Override
    public Collection<PageImage> getPageImages(Long pageId) {
        return findPageById(pageId).get().getPageImageSet();
    }

    @Override
    public Collection<PageVideo> getPageVideos(Long pageId) {
        return findPageById(pageId).get().getPageVideoSet();
    }

}
