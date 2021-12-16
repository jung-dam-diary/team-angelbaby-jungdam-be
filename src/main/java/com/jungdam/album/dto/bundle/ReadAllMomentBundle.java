package com.jungdam.album.dto.bundle;

public class ReadAllMomentBundle {

    private final Long memberId;
    private final Long albumId;

    public ReadAllMomentBundle(final Long memberId, final Long albumId) {
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
