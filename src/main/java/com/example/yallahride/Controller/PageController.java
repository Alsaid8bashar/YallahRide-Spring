package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Page;
import com.example.yallahride.Entity.PageContent;
import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Service.Interface.PageService;
import jakarta.validation.Valid;
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
        return new ResponseEntity<>(pageService.findPageById(id).get(), OK);
    }

    @PostMapping("save_page")
    public ResponseEntity<Page> savePage(@Valid @RequestBody Page page) {
        return new ResponseEntity<>(pageService.savePage(page), CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePage(@PathVariable Long id) {
        pageService.deletePageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Page>> getPages() {
        return new ResponseEntity<>(pageService.findAllPages(), HttpStatus.OK);
    }

    @PostMapping("add_content")
    public ResponseEntity<Page> addContent(@PathVariable Long id, PageContent pageContent) {
        return new ResponseEntity<>(pageService.addContent(id, pageContent), CREATED);
    }

    @PostMapping("add_image")
    public ResponseEntity<Page> addImage(@PathVariable Long id, PageImage pageImage) {
        return new ResponseEntity<>(pageService.addImage(id, pageImage), CREATED);
    }

    @PostMapping("add_video")
    public ResponseEntity<Page> addVideo(@PathVariable Long id, PageVideo pageVideo) {
        return new ResponseEntity<>(pageService.addVideo(id, pageVideo), CREATED);
    }

    @GetMapping("contents/{pageId}")
    public ResponseEntity<Collection<PageContent>> getPageContents(@PathVariable Long pageId) {
        return new ResponseEntity<>(pageService.getPageContents(pageId), HttpStatus.OK);
    }

    @GetMapping("images/{pageId}")
    public ResponseEntity<Collection<PageImage>> getPageImages(@PathVariable Long pageId) {
        return new ResponseEntity<>(pageService.getPageImages(pageId), HttpStatus.OK);
    }

    @GetMapping("videos/{pageId}")
    public ResponseEntity<Collection<PageVideo>> getPageVideos(@PathVariable Long pageId) {
        return new ResponseEntity<>(pageService.getPageVideos(pageId), HttpStatus.OK);
    }

    @GetMapping("/number_Of_pages")
    public ResponseEntity<Long> getNumberOfPage() {
        return new ResponseEntity<>(pageService.getNumberOfPages(), HttpStatus.OK);
    }

}
