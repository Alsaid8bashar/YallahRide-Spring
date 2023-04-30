package com.example.yallahride.Service;

import com.example.yallahride.Entity.PageContent;

import java.util.List;
import java.util.Optional;

public interface PageContentService {
    void savePageContent(PageContent pageContent);

    Optional<PageContent> findPageContentById(Long id);

    List<PageContent> findAllPageContents();

    void deleteAllPageContents();

    void deletePageContentById(Long id);

    long getNumberOfPageContent();
}
