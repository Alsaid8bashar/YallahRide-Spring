package com.example.yallahride.Service.implementation;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.yallahride.Service.Interface.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Component
public class AWSS3Service implements FileService {

    @Autowired
    AmazonS3 s3client;
    @Value("${aws.s3.bucketName}")
    String bucket;


    @Override
    public void uploadFile(MultipartFile multipartFile, String key) {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try {
            s3client.putObject(bucket, key, multipartFile.getInputStream(), objectMetadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(String key) {
        s3client.deleteObject(bucket, key);
    }

    @Override

    public void deleteFiles(java.util.List<String> keys) {
        String arrayKeys[] = new String[keys.size()];
        DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucket)
                .withKeys(Arrays.toString(keys.toArray(arrayKeys)));
        s3client.deleteObjects(delObjReq);
    }

    @Override
    public StreamingResponseBody displayFile(String key) {
        S3Object s3object = s3client.getObject(bucket, key);
        S3ObjectInputStream inputStream = s3object.getObjectContent();

        StreamingResponseBody body = outputStream -> {
            int numberOfBytesToWrite = 0;
            byte[] data = new byte[1024];
            while ((numberOfBytesToWrite = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, numberOfBytesToWrite);
            }
            inputStream.close();
        };

        return body;
    }
}