package com.cwr.service.impl.oss;

import com.cwr.config.CloudflareR2Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Component
public class CloudflareR2Client {

    private final CloudflareR2Properties cloudflareR2Properties;

    public CloudflareR2Client(CloudflareR2Properties cloudflareR2Properties) {
        this.cloudflareR2Properties = cloudflareR2Properties;
    }

    @Bean
    public S3Client s3Client() {
        String accessKeyId = cloudflareR2Properties.getAccessKeyId();
        String secretAccessKey = cloudflareR2Properties.getSecretAccessKey();
        String endpoint = cloudflareR2Properties.getEndpoint();
        String region = cloudflareR2Properties.getRegion();

        return S3Client.builder()
                .httpClient(ApacheHttpClient.builder().build())
                .endpointOverride(URI.create(endpoint)) // 替换为你的 Cloudflare R2 endpoint
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKeyId, secretAccessKey))) // 替换为你的凭证
                .region(Region.of(region))  // Cloudflare R2 不关心 AWS 区域，但 SDK 需要一个区域代码
                .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build()) // Cloudflare R2 需要路径风格访问
                .build();
    }
}