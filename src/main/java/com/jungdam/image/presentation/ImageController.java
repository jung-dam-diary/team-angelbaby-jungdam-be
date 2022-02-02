package com.jungdam.image.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.image.application.FileProcessService;
import com.jungdam.image.config.FileFolder;
import com.jungdam.image.dto.bundle.UploadBundle;
import com.jungdam.image.dto.response.UploadResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final FileProcessService fileProcessService;

    public ImageController(FileProcessService fileProcessService) {
        this.fileProcessService = fileProcessService;
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto<UploadResponse>> upload(
        @RequestParam("image") MultipartFile multipartFile) {
        UploadBundle bundle = new UploadBundle(multipartFile, FileFolder.TEMPORARY);
        UploadResponse response = fileProcessService.uploadImage(bundle);

        return ResponseDto.of(ResponseMessage.IMAGE_UPLOAD_SUCCESS, response);
    }
}