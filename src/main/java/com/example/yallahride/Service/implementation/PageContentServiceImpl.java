package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Repository.PageContentRepository;
import com.example.yallahride.Service.Interface.PageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageContentServiceImpl implements PageContentService {

    @Autowired
    PageContentRepository pageContentRepository;

    @Override
    public void savePageContent(PageContent pageContent) {
        pageContentRepository.save(pageContent);
    }

    @Override
    public Optional<PageContent> findPageContentById(Long id) {
        return pageContentRepository.findById(id);
    }

    @Override
    public List<PageContent> findAllPageContents() {
        return pageContentRepository.findAll();
    }

    @Override
    public void deleteAllPageContents() {
        pageContentRepository.deleteAll();
    }

    @Override
    public void deletePageContentById(Long id) {
        pageContentRepository.deleteById(id);
    }

    @Override
    public long getNumberOfPageContent() {
        return pageContentRepository.count();
    }
}
