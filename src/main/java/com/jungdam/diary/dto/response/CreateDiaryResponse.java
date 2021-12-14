package com.jungdam.diary.dto.response;

public class CreateDiaryResponse {

    private final Long albumId;
    private final Long diaryId;

    public CreateDiaryResponse(Long albumId, Long diaryId) {
        this.albumId = albumId;
        this.diaryId = diaryId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Long getDiaryId() {
        return diaryId;
    }
}