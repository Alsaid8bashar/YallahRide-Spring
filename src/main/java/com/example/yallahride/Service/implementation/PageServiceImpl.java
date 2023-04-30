package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Repository.PageRepository;
import com.example.yallahride.Service.Interface.PageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageServiceImpl implements PageService {
    private final PageRepository pageRepository;

    public PageServiceImpl(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }


    @Override
    public void savePage(Page page) {
        pageRepository.save(page);
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
}
