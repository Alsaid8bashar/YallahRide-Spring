package com.example.yallahride.Service.Interface;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface FileService {

    void uploadFile(MultipartFile multipartFile, String bucket);

    void deleteFile(String key, String bucket);

    StreamingResponseBody displayFile(String key, String bucket);

}
