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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    PageImagesRepository pageImagesRepository;
    @Autowired
    PageContentRepository pageContentRepository;
    @Autowired
    PageVideoRepository pageVideoRepository;
    private final PageRepository pageRepository;

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
    public List<PageImage> getAllPageImagesByPage(Page page) {
        List<Long>pageVideoIds = new ArrayList<>();
        for(PageImage element : page.getPageImageSet()){
            pageVideoIds.add(element.getId());
        }
        return pageImagesRepository.findAllById(pageVideoIds);
    }

    @Override
    public List<PageVideo> getAllPageVideosByPage(Page page) {
        List<Long>pageVideoIds = new ArrayList<>();
        for(PageVideo element : page.getPageVideoSet()){
            pageVideoIds.add(element.getId());
        }
        return pageVideoRepository.findAllById(pageVideoIds);
    }

    @Override
    public List<PageContent> getAllPageContentsByPage(Page page) {
        List<Long>pageVideoIds = new ArrayList<>();
        for(PageContent element : page.getPageContentSet()){
            pageVideoIds.add(element.getId());
        }
        return pageContentRepository.findAllById(pageVideoIds);
    }
}
