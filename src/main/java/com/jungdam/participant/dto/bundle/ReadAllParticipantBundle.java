package com.jungdam.participant.dto.bundle;

public class ReadAllParticipantBundle {

    private final Long albumId;
    private final Long memberId;

    public ReadAllParticipantBundle(Long albumId, Long memberId) {
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
