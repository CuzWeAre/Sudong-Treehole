package com.cwr.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 这个东西不应该被重写为Record类，因为需要setter方法
 */


@Data
@Configuration
@ConfigurationProperties(prefix = "oss.cloudflare")
public class CloudflareR2Properties {
    private String accessKeyId;
    private String secretAccessKey;
    private String bucketName;
    private String region;
    private String endpoint;
}
