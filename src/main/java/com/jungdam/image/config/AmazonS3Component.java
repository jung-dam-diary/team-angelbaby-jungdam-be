package com.jungdam.image.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AmazonS3Component {

    @Value("${cloud.aws.s3.bucket}")
    public String bucketName;
    @Value("${cloud.aws.s3.folder.avatar}")
    public String avatarFolder;
    @Value("${cloud.aws.s3.folder.diary}")
    public String diaryFolder;
    @Value("${cloud.aws.s3.folder.thumbnail}")
    public String thumbnailFolder;
    @Value("${cloud.aws.s3.folder.temp}")
    public String temporaryFolder;

    public String getTemporaryFolder() {
        return temporaryFolder;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getAvatarFolder() {
        return avatarFolder;
    }

    public String getDiaryFolder() {
        return diaryFolder;
    }

    public String getThumbnailFolder() {
        return thumbnailFolder;
    }
}
