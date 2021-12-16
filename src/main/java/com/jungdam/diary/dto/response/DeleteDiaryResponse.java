package com.jungdam.diary.dto.response;

public class DeleteDiaryResponse {

    private final Long albumId;

    public DeleteDiaryResponse(Long albumId) {
        this.albumId = albumId;
    }

    public Long getAlbumId() {
        return albumId;
    }
}