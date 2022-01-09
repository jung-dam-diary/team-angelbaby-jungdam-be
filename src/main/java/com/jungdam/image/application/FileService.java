package com.jungdam.image.application;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.jungdam.image.config.FileFolder;
import java.io.InputStream;

public interface FileService {

    void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName);

    void deleteFile(String fileName);

    String getFileUrl(String fileName);

    String getFileFolder(FileFolder fileFolder);
}
