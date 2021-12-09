package com.jungdam.image.controller;

import com.jungdam.image.service.S3Uploader;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class ImageUploadController {

    private final S3Uploader s3Uploader;

    public ImageUploadController(S3Uploader s3Uploader) {
        this.s3Uploader = s3Uploader;
    }

    @GetMapping("/images/health")
    public String healthCheck() {
        return "ok";
    }

    @PostMapping("/images")
    public String upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        // TODO: dto
        return s3Uploader.upload(multipartFile, "temp");
    }
}
