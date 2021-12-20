package com.jungdam.diary.dto.bundle;

public class ReadGroupStoryBookBundle {

    private final Long memberId;
    private final Long albumId;

    public ReadGroupStoryBookBundle(Long memberId, Long albumId) {
        this.memberId = memberId;
        this.albumId = albumId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getAlbumId() {
        return albumId;
    }
}
