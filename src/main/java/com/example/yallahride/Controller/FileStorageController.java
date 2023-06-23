package com.example.yallahride.Controller;


import com.example.yallahride.Service.Interface.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("storage")
public class FileStorageController {

    @Autowired
    FileService fileService;

    @GetMapping("/{key}")
    public ResponseEntity<StreamingResponseBody> getFile(@PathVariable String key) {
        return new ResponseEntity<>(fileService.displayFile(key), OK);
    }

    @GetMapping("/url/{key}")
    public ResponseEntity<Map<String, String>> getObjectUrl(@PathVariable String key) {
        String objectUrl = fileService.getObjectUrl(key);
        Map<String, String> response = new HashMap<>();
        response.put("url", objectUrl);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile multipartFile) {
        return new ResponseEntity<>(fileService.uploadFile(multipartFile), CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteFile(@RequestParam String key) {
        fileService.deleteFile(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
