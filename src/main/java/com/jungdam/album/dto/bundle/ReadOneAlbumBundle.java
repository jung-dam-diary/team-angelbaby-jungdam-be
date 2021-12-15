package com.jungdam.album.dto.bundle;

public class ReadOneAlbumBundle {

    private final Long memberId;
    private final Long albumId;

    public ReadOneAlbumBundle(Long memberId, Long albumId) {
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