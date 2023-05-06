package com.example.yallahride.Controller;

import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Service.Interface.PageContentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("page-content")
public class PageContentController {
    @Autowired
    private PageContentService pageContentService;


    @GetMapping("/{id}")
    public ResponseEntity<PageContent> getPageContent(@PathVariable Long id) {
        return new ResponseEntity<>(pageContentService.findPageContentById(id).get(), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PageContent> savePage(@Valid @RequestBody PageContent pageContent) {
        return new ResponseEntity<>(pageContentService.savePageContent(pageContent), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deletePageContent(@PathVariable Long id) {
        pageContentService.deletePageContentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PageContent>> getPageContents() {
        return new ResponseEntity<>(pageContentService.findAllPageContents(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus> deletePagesContents() {
        pageContentService.deleteAllPageContents();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
