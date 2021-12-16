package com.jungdam.album.dto.bundle;

public class ReadAllMomentBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long cursorId;
    private final Integer size;

    public ReadAllMomentBundle(final Long memberId, final Long albumId, final Long cursorId,
        final Integer size) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.cursorId = cursorId;
        this.size = size;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Long getCursorId() {
        return cursorId;
    }

    public Integer getSize() {
        return size;
    }
}
