package com.jungdam.diary_photo.dto.response;

public class DiaryPhotosInfoResponse {

    private final Long id;
    private final String image;

    public DiaryPhotosInfoResponse(Long id, String image) {
        this.id = id;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}