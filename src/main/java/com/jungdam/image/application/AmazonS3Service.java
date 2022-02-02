package com.jungdam.image.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.jungdam.image.config.AmazonS3Component;
import com.jungdam.image.config.FileFolder;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AmazonS3Service implements FileService {

    private static final Logger log = LoggerFactory.getLogger(AmazonS3Service.class);

    private final AmazonS3 amazonS3;
    private final AmazonS3Component amazonS3Component;

    public AmazonS3Service(AmazonS3 amazonS3,
        AmazonS3Component amazonS3Component) {
        this.amazonS3 = amazonS3;
        this.amazonS3Component = amazonS3Component;
    }

    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata,
        String fileName) {
        amazonS3.putObject(
            new PutObjectRequest(amazonS3Component.getBucketName(), fileName, inputStream,
                objectMetadata).withCannedAcl(CannedAccessControlList.PublicReadWrite));
    }

    @Override
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(
            new DeleteObjectRequest(amazonS3Component.getBucketName(), fileName)
        );
    }

    @Override
    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(amazonS3Component.getBucketName(), fileName)
            .toString();
    }

    @Override
    public String getFileFolder(FileFolder fileFolder) {
        return fileFolder.getFileFolder(amazonS3Component);
    }
}