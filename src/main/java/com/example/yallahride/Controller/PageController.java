package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Service.Interface.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("page")
public class PageController {
    @Autowired
    private PageService pageService;


    @GetMapping("/{id}")
    public ResponseEntity<Page> getPage(@PathVariable Long id) {
        return new ResponseEntity<>(pageService.findPageById(id), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Page> savePage(@RequestBody Page page) {
        return new ResponseEntity<>(pageService.savePage(page), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deletePage(@PathVariable Long id) {
        pageService.deletePageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Page>> getPages() {
        return new ResponseEntity<>(pageService.findAllPages(), HttpStatus.OK);
    }

    @PostMapping("add/content")
    public ResponseEntity<Page> addContent(@RequestParam Long id, PageContent pageContent) {
        return new ResponseEntity<>(pageService.addContent(id, pageContent), CREATED);
    }

    @PostMapping("/add/image")
    public ResponseEntity<Page> addImage(@RequestParam Long id,@ModelAttribute PageImage pageImage) {
        return new ResponseEntity<>(pageService.addImage(id, pageImage), CREATED);
    }

    @PostMapping("/add/video")
    public ResponseEntity<Page> addVideo(@RequestParam Long id,@ModelAttribute PageVideo pageVideo) {
        return new ResponseEntity<>(pageService.addVideo(id, pageVideo), CREATED);
    }

    @GetMapping("/contents")
    public ResponseEntity<Collection<PageContent>> getPageContents(@RequestParam Long pageId) {
        return new ResponseEntity<>(pageService.getPageContents(pageId), HttpStatus.OK);
    }

    @GetMapping("/images")
    public ResponseEntity<Collection<PageImage>> getPageImages(@RequestParam Long pageId) {
        return new ResponseEntity<>(pageService.getPageImages(pageId), HttpStatus.OK);
    }

    @GetMapping("/videos")
    public ResponseEntity<Collection<PageVideo>> getPageVideos(@RequestParam Long pageId) {
        return new ResponseEntity<>(pageService.getPageVideos(pageId), HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Long> getNumberOfPage() {
        return new ResponseEntity<>(pageService.getNumberOfPages(), HttpStatus.OK);
    }

}
