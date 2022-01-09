package com.jungdam.image.dto.bundle;

import com.jungdam.image.config.FileFolder;
import org.springframework.web.multipart.MultipartFile;

public class UploadBundle {

    private final MultipartFile multipartFile;
    private final FileFolder fileFolder;

    public UploadBundle(MultipartFile multipartFile, FileFolder fileFolder) {
        this.multipartFile = multipartFile;
        this.fileFolder = fileFolder;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public FileFolder getFileFolderName() {
        return fileFolder;
    }
}