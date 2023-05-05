package com.example.yallahride.Controller;

import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Service.Interface.PageVideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("page_video")
public class PageVideoController {

    @Autowired
    private PageVideoService pageVideoService;

    @GetMapping("/{id}")
    public ResponseEntity<PageVideo> getPageVideo(@PathVariable Long id) {
        return new ResponseEntity<>(pageVideoService.findPageVideoById(id).get(), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PageVideo> savePage(@Valid @RequestBody PageVideo PageVideo) {
        return new ResponseEntity<>(pageVideoService.savePageVideo(PageVideo), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deletePageVideo(@PathVariable Long id) {
        pageVideoService.deletePageVideoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PageVideo>> getPageVideos() {
        return new ResponseEntity<>(pageVideoService.findAllPageVideos(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus> deletePagesVideos() {
        pageVideoService.deleteAllPageVideos();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
