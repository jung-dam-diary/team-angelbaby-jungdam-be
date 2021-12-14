package com.jungdam.participant.dto.bundle;

public class ReadAllParticipantBundle {

    private final Long albumId;

    public ReadAllParticipantBundle(Long albumId) {
        this.albumId = albumId;
    }

    public Long getAlbumId() {
        return albumId;
    }
}
