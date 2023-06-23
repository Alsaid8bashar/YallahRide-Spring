package com.example.yallahride.Service.implementation;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.example.yallahride.Service.Interface.FileService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AWSS3Service implements FileService {

    final AmazonS3 s3client;
    Logger logger = LoggerFactory.getLogger(AWSS3Service.class);
    @Value("${aws.s3.bucketName}")
    String bucket;

    @Autowired
    public AWSS3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }


    @Override
    public String uploadFile(MultipartFile multipartFile) {
        String key = UUID.randomUUID() + multipartFile.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try {
            s3client.putObject(bucket, key, multipartFile.getInputStream(), objectMetadata);
            return key;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public void deleteFile(String key) {
        if (key == null)
            return;

        boolean fileExists = s3client.doesObjectExist(bucket, key);
        if (fileExists) {
            s3client.deleteObject(bucket, key);
        } else {
            throw new FileNotFoundException("File not found: " + key);
        }
    }

    @Override
    public void deleteFiles(java.util.List<String> keys) {
        List<String> keysToDelete = new ArrayList<>();
        for (String key : keys) {
            boolean fileExists = s3client.doesObjectExist(bucket, key);
            if (fileExists) {
                keysToDelete.add(key);
            } else {
                logger.warn("File not found: {}", key);
            }
        }

        if (!keysToDelete.isEmpty()) {
            String[] arrayKeys = keysToDelete.toArray(new String[0]);
            DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucket)
                    .withKeys(arrayKeys);
            s3client.deleteObjects(delObjReq);
        }
    }

    @SneakyThrows
    @Override
    public StreamingResponseBody displayFile(String key) {
        boolean fileExists = s3client.doesObjectExist(bucket, key);
        if (!fileExists) {
            throw new FileNotFoundException("Key not found: " + key);
        }
        S3Object s3object = s3client.getObject(bucket, key);
        S3ObjectInputStream inputStream = s3object.getObjectContent();

        return outputStream -> {
            int numberOfBytesToWrite = 0;
            byte[] data = new byte[1024];
            while ((numberOfBytesToWrite = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, numberOfBytesToWrite);
            }
            inputStream.close();
        };
    }


    @Override
    public List<String> getBucketKeys() {
        ObjectListing objectListing = s3client.listObjects(bucket);
        List<String> keys = new ArrayList<>();
        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
            keys.add(os.getKey());
        }
        return keys;
    }

    public String getObjectUrl(String key) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, key)
                .withMethod(HttpMethod.GET)
                .withExpiration(Date.from(Instant.now().plus(Duration.ofDays(7))));

        URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

}