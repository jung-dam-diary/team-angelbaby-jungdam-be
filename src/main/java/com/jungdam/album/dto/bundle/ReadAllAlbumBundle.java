package com.jungdam.album.dto.bundle;

public class ReadAllAlbumBundle {

    private final Long memberId;

    public ReadAllAlbumBundle(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
