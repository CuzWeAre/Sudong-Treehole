package com.cwr.service;

import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;

public interface OssService {
    void listObjects();
    PutObjectResponse putObject(MultipartFile file, String key) throws IOException;
}
