package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.PageContent;

import java.util.List;
import java.util.Optional;

public interface PageContentService {
    PageContent savePageContent(PageContent pageContent);

    PageContent findPageContentById(Long id);

    List<PageContent> findAllPageContents();

    PageContent updatePageContent(PageContent pageContent);

    void deleteAllPageContents();

    void deletePageContentById(Long id);

    long getNumberOfPageContent();
}
