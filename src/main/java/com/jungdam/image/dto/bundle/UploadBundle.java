package com.jungdam.image.dto.bundle;

import org.springframework.web.multipart.MultipartFile;

public class UploadBundle {

    private final MultipartFile multipartFile;
    private final String directoryName;

    public UploadBundle(MultipartFile multipartFile, String directoryName) {
        this.multipartFile = multipartFile;
        this.directoryName = directoryName;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public String getDirectoryName() {
        return directoryName;
    }
}
