package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Exceptions.EntityNotFoundException;
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
    public Page findPageById(Long id) {
        return unwrapPage(pageRepository.findById(id),id);
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
    public Page addContent(Long pageId, PageContent pageContent) {
        Page page = unwrapPage(pageRepository.findById(pageId),pageId);
        page.addContent(pageContent);
        return savePage(page);
    }

    @Override
    public Page addImage(Long pageId, PageImage pageImage) {
        Page page = unwrapPage(pageRepository.findById(pageId),pageId);
        page.addImage(pageImage);
        return savePage(page);
    }

    @Override
    public Page addVideo(Long pageId, PageVideo pageVideo) {
        Page page = unwrapPage(pageRepository.findById(pageId),pageId);
        page.addVideo(pageVideo);
        return savePage(page);
    }

    @Transactional
    @Override
    public Collection<PageContent> getPageContents(Long pageId) {
        return unwrapPage(pageRepository.findById(pageId),pageId).getPageContentSet();
    }

    @Override
    public Collection<PageImage> getPageImages(Long pageId) {
        return unwrapPage(pageRepository.findById(pageId),pageId).getPageImageSet();
    }

    @Override
    public Collection<PageVideo> getPageVideos(Long pageId) {
        return unwrapPage(pageRepository.findById(pageId),pageId).getPageVideoSet();
    }

    static Page unwrapPage(Optional<Page> page, Long id) {
        if (page.isPresent()) return page.get();
        else throw new EntityNotFoundException(id, Page.class);
    }
}
