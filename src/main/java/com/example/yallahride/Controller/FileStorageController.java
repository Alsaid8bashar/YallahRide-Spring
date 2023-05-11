package com.example.yallahride.Controller;


import com.example.yallahride.Service.Interface.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("storage")

public class FileStorageController {

    @Autowired
    FileService fileService;

    @GetMapping("/{key}")
    public ResponseEntity<StreamingResponseBody> getFile(@PathVariable String key) {
        return new ResponseEntity<>(fileService.displayFile(key, "yallah-ride-bucket"),OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<HttpStatus> uploadFile(@RequestPart("videoFile") MultipartFile multipartFile) {
//        fileService.uploadFile(multipartFile, "yallah-ride-bucket");
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteFile(@RequestParam String key) {
        fileService.deleteFile(key, "yallah-ride-bucket");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
