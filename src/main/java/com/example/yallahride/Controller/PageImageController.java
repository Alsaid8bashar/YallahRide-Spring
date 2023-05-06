package com.example.yallahride.Controller;

import com.example.yallahride.Entity.PageImage;
import com.example.yallahride.Service.Interface.PageImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
@RestController
@RequestMapping("page-image")
public class PageImageController {
    @Autowired
    private PageImageService pageImageService;

    @GetMapping("/{id}")
    public ResponseEntity<PageImage> getPageImage(@PathVariable Long id) {
        return new ResponseEntity<>(pageImageService.findPageImageById(id).get(), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PageImage> savePage(@Valid @RequestBody PageImage PageImage) {
        return new ResponseEntity<>(pageImageService.savePageImage(PageImage), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deletePageImage(@PathVariable Long id) {
        pageImageService.deleteImageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PageImage>> getPageImages() {
        return new ResponseEntity<>(pageImageService.findAllPageImages(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus> deletePagesImages() {
        pageImageService.deleteAllPageImages();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
