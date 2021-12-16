package com.jungdam.comment.dto.response;

public class DeleteCommentResponse {

    private final Long albumId;
    private final Long diaryId;

    public DeleteCommentResponse(Long albumId, Long diaryId) {
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