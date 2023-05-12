package com.example.yallahride.Service.Interface;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

public interface FileService {


    String uploadFile(MultipartFile multipartFile);

    void deleteFile(String key);


    void deleteFiles(List<String> strings);

    StreamingResponseBody displayFile(String key);

    List<String> getBucketKeys();

}
