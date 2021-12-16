package com.jungdam.participant.dto.bundle;

public class CheckParticipantBundle {

    private final Long memberId;
    private final Long albumId;

    public CheckParticipantBundle(Long memberId, Long albumId) {
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