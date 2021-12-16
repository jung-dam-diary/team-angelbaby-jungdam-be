package com.jungdam.album.dto.response;

public class DeleteAlbumResponse {

    public final Long albumId;
    public final Long memberId;

    public DeleteAlbumResponse(Long albumId, Long memberId) {
        this.albumId = albumId;
        this.memberId = memberId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
