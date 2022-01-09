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

    // TODO : catch AWS Error into Custom Error
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

    // FIXME : 더 좋은 코드로 짤 수 없을지 피드백 부탁드립니다!
    @Override
    public String getFileFolder(FileFolder fileFolder) {
        String folder;
        if (fileFolder == FileFolder.AVATAR_IMAGES) {
            folder = amazonS3Component.getAvatarFolder();
        } else if (fileFolder == FileFolder.DIARY_IMAGES) {
            folder = amazonS3Component.getDiaryFolder();
        } else if (fileFolder == FileFolder.THUMBNAIL_IMAGES) {
            folder = amazonS3Component.getThumbnailFolder();
        } else {
            folder = amazonS3Component.getTemporaryFolder();
        }
        return folder;
    }

    // ------------------------------------------------------------
    /*
    public UploadResponse upload(UploadBundle bundle) throws IOException {
        File uploadFile = convert(bundle.getMultipartFile())
            .orElseThrow(
                () -> new FileConverterException(ErrorMessage.FAILURE_FILE_CONVERT)
            );
        String uploadImageUrl = upload(uploadFile, bundle.getFileFolderName());

        return new UploadResponse(uploadImageUrl);
    }

    public String upload(File uploadFile, String dirName) {
        String fileName = dirName + URL_SLASH + UUID.randomUUID();

        return putS3(uploadFile, fileName);
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
    */
}