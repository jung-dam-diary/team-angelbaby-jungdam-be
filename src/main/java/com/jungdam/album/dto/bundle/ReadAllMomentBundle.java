package com.jungdam.album.dto.bundle;

public class ReadAllMomentBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long cursorId;
    private final Integer pageSize;

    public ReadAllMomentBundle(final Long memberId, final Long albumId, final Long cursorId,
        final Integer pageSize) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.cursorId = cursorId;
        this.pageSize = pageSize;
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

    public Integer getPageSize() {
        return pageSize;
    }
}
