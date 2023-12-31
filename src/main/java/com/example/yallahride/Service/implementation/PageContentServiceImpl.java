package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.PageContentRepository;
import com.example.yallahride.Service.Interface.PageContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PageContentServiceImpl implements PageContentService {

    final PageContentRepository pageContentRepository;

    public PageContentServiceImpl(PageContentRepository pageContentRepository) {
        this.pageContentRepository = pageContentRepository;
    }

    @Override
    public PageContent savePageContent(PageContent pageContent) {
        return pageContentRepository.save(pageContent);
    }

    @Override
    public PageContent findPageContentById(Long id) {
        return unwrapPageContent(pageContentRepository.findById(id), id);
    }

    @Override
    public List<PageContent> findAllPageContents() {
        return pageContentRepository.findAll();
    }


    @Override
    public PageContent updatePageContent(PageContent pageContent) {
        return pageContentRepository.save(pageContent);
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

    static PageContent unwrapPageContent(Optional<PageContent> pageContent, Long id) {
        if (pageContent.isPresent()) return pageContent.get();
        else throw new EntityNotFoundException(id, PageContent.class);
    }
}
