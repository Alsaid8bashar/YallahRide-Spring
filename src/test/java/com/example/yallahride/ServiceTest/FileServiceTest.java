package com.example.yallahride.ServiceTest;

import com.example.yallahride.Service.Interface.FileService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileServiceTest {

    @Autowired
    FileService fileService;


    @Test
    public void testUploadFile() {
        MultipartFile multipartFile = new MockMultipartFile("file.txt", "Hello, World!".getBytes());
        String key = fileService.uploadFile(multipartFile);
        Assertions.assertTrue(fileService.getBucketKeys().contains(key));
    }


    @Test
    public void testDeleteFile() {
        MultipartFile multipartFile = new MockMultipartFile("file.txt", "Hello, World!".getBytes());
        String key = fileService.uploadFile(multipartFile);

        fileService.deleteFile(key);
        Assertions.assertFalse(fileService.getBucketKeys().contains(key));

    }

    @Test
    public void testDeleteFiles() {
        MultipartFile multipartFile1 = new MockMultipartFile("file.txt", "Hello, World!".getBytes());
        MultipartFile multipartFile2 = new MockMultipartFile("file.txt", "Hello, World!".getBytes());


        String key1 = fileService.uploadFile(multipartFile1);
        String key2 = fileService.uploadFile(multipartFile2);


        java.util.List<String> keys = Arrays.asList(key1, key2);
        fileService.deleteFiles(keys);
        Assertions.assertFalse(fileService.getBucketKeys().contains(key1));
        Assertions.assertFalse(fileService.getBucketKeys().contains(key2));
    }
    @AfterAll
    public void cleanUp(){
        fileService.deleteFiles(fileService.getBucketKeys());
    }

}
