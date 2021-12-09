package com.jungdam.image.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.image.application.S3Uploader;
import com.jungdam.image.dto.bundle.UploadBundle;
import com.jungdam.image.dto.response.UploadResponse;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class ImageController {

    private final S3Uploader s3Uploader;

    public ImageController(S3Uploader s3Uploader) {
        this.s3Uploader = s3Uploader;
    }

    // TODO: Security
    @PostMapping("/images")
    public ResponseEntity<ResponseDto<UploadResponse>> upload(
        @RequestParam("images") MultipartFile multipartFile)
        throws IOException {
        UploadBundle bundle = new UploadBundle(multipartFile, "temp");
        UploadResponse response = s3Uploader.upload(bundle);

        return ResponseEntity.status(
                ResponseMessage.IMAGE_UPLOAD_SUCCESS.getStatus()
            )
            .body(
                ResponseDto.of(ResponseMessage.IMAGE_UPLOAD_SUCCESS, response)
            );
    }
}
