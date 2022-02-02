package com.jungdam.image.application;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.image.FileConverterException;
import com.jungdam.image.config.FileFolder;
import com.jungdam.image.dto.bundle.UploadBundle;
import com.jungdam.image.dto.response.UploadResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileProcessService {

    private static final Logger log = LoggerFactory.getLogger(FileProcessService.class);
    private static final int FILE_FOLDER_NAME_INDEX = 2;
    private static final int FILE_NAME_INDEX = 1;
    private static final String URL_DIVIDER = "/";
    private static final String FILE_NAME_EXTENSION_DIVIDER = ".";

    private final FileService amazonS3Service;

    public FileProcessService(FileService amazonS3Service) {
        this.amazonS3Service = amazonS3Service;
    }

    public UploadResponse uploadImage(UploadBundle uploadBundle) {
        MultipartFile file = uploadBundle.getMultipartFile();
        FileFolder fileFolder = uploadBundle.getFileFolderName();
        String fileName =
            amazonS3Service.getFileFolder(fileFolder) + createFileName(file.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Service.uploadFile(inputStream, objectMetadata, fileName);
        } catch (IOException exception) {
            log.error(String.format("파일 변환 중 에러가 발생했습니다 (%s)", file.getOriginalFilename()));
            throw new FileConverterException(ErrorMessage.FAILURE_FILE_CONVERT);
        }

        return new UploadResponse(amazonS3Service.getFileUrl(fileName));
    }

    private String createFileName(String originalFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(FILE_NAME_EXTENSION_DIVIDER));
    }

    public void deleteImage(String url) {
        amazonS3Service.deleteFile(getFileName(url));
    }

    private String getFileName(String url) {
        String[] paths = url.split(URL_DIVIDER);
        return paths[paths.length - FILE_FOLDER_NAME_INDEX]
            + URL_DIVIDER
            + paths[paths.length - FILE_NAME_INDEX];
    }

}
