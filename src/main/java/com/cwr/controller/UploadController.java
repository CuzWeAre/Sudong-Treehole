package com.cwr.controller;

import com.cwr.pojo.ApiResponse;
import com.cwr.service.OssService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Tag(name = "文件上传接口",description = "管理文件的上传")
@Slf4j
@RestController
@RequestMapping("/oss")
public class UploadController {

    private final OssService ossService;

    public UploadController(OssService ossService) {
        this.ossService = ossService;
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> upload(@RequestPart("image") MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        String newFileName = UUID.randomUUID() + Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf("."));
        log.info("文件上传: {} ", newFileName);
        PutObjectResponse putObjectResponse = ossService.putObject(image,newFileName);
        return ApiResponse.success("https://treehole.oss.whoa.world/" + newFileName);
    }

    @GetMapping("/list")
    public ApiResponse<Object> list() {
        ossService.listObjects();
        return ApiResponse.success();
    }
}
