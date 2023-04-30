package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Page;

import java.util.List;
import java.util.Optional;

public interface PageService {

    void savePage(Page page);

    Optional<Page> findPageById(Long id);

    List<Page> findAllPages();

    void deleteAllPages();

    void deletePageById(Long id);

    long getNumberOfPages();
}
