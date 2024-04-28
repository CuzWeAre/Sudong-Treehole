package com.cwr.service.impl.oss;

import com.cwr.config.CloudflareR2Properties;
import com.cwr.service.OssService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;


@Service
public class CloudflareR2Impl implements OssService {

    private final S3Client s3Client;
    private final CloudflareR2Properties cloudflareR2Properties;

    public CloudflareR2Impl(S3Client s3Client, CloudflareR2Properties cloudflareR2Properties) {
        this.s3Client = s3Client;
        this.cloudflareR2Properties = cloudflareR2Properties;
    }


    @Override
    public void listObjects() {
        String bucketName = cloudflareR2Properties.getBucketName();

        ListObjectsRequest listObjects = ListObjectsRequest.builder().bucket(bucketName).build();

        s3Client.listObjects(listObjects).contents().forEach(s3Object -> {
            System.out.println("* " + s3Object.key());
        });
    }

    @Override
    public PutObjectResponse putObject(MultipartFile file, String key) throws IOException {
        String bucketName = cloudflareR2Properties.getBucketName();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(key).build();
        return s3Client.putObject(
                putObjectRequest,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize())
        );
    }
}
