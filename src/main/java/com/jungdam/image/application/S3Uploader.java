package com.jungdam.image.application;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.s3.FileConverterException;
import com.jungdam.image.dto.bundle.UploadBundle;
import com.jungdam.image.dto.response.UploadResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class S3Uploader {

    private static final Logger log = LoggerFactory.getLogger(S3Uploader.class);

    private static final String URL_SLASH = "/";
    private static final String DIRECTORY_PROPERTY = "user.dir";

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucketName;

    public S3Uploader(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public UploadResponse upload(UploadBundle bundle) throws IOException {
        File uploadFile = convert(bundle.getMultipartFile())
            .orElseThrow(
                () -> new FileConverterException(ErrorMessage.FAILURE_FILE_CONVERT)
            );
        String uploadImageUrl = upload(uploadFile, bundle.getDirectoryName());

        return new UploadResponse(uploadImageUrl);
    }

    public String upload(File uploadFile, String dirName) {
        String fileName = dirName + URL_SLASH + UUID.randomUUID();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    public String putS3(File uploadFile, String fileName) {

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName,
                uploadFile);
            amazonS3Client.putObject(
                putObjectRequest.withCannedAcl(
                    CannedAccessControlList.PublicRead)
            );
        } finally {
            removeNewFile(uploadFile);
        }

        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }

    public void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
        } else {
            log.warn("File delete fail");
        }
    }

    public Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(
            System.getProperty(DIRECTORY_PROPERTY) + URL_SLASH + file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}