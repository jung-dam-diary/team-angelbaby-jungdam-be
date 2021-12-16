package com.jungdam.participant.dto.response;

public class CheckParticipantResponse {

    private final Long albumId;
    private final boolean existence;

    public CheckParticipantResponse(Long albumId, boolean existence) {
        this.albumId = albumId;
        this.existence = existence;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public boolean isExistence() {
        return existence;
    }
}