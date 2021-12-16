package com.jungdam.invitation.dto.response;

import java.time.LocalDate;

public class ReadAllInvitationResponse {

    private final Long invitationId;
    private final LocalDate invitationCreatedAt;
    private final String albumTitle;

    public ReadAllInvitationResponse(Long invitationId, LocalDate invitationCreatedAt, String albumTitle) {
        this.invitationId = invitationId;
        this.invitationCreatedAt = invitationCreatedAt;
        this.albumTitle = albumTitle;
    }

    public static ReadAllInvitationResponseBuilder builder() {
        return new ReadAllInvitationResponseBuilder();
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public LocalDate getInvitationCreatedAt() {
        return invitationCreatedAt;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public static class ReadAllInvitationResponseBuilder {

        private Long invitationId;
        private LocalDate invitationCreatedAt;
        private String albumTitle;

        private ReadAllInvitationResponseBuilder() {
        }

        public ReadAllInvitationResponseBuilder invitationId(final Long invitationId) {
            this.invitationId = invitationId;
            return this;
        }

        public ReadAllInvitationResponseBuilder invitationCreatedAt(final LocalDate invitationCreatedAt) {
            this.invitationCreatedAt = invitationCreatedAt;
            return this;
        }

        public ReadAllInvitationResponseBuilder albumTitle(final String albumTitle) {
            this.albumTitle = albumTitle;
            return this;
        }

        public ReadAllInvitationResponse build() {
            return new ReadAllInvitationResponse(this.invitationId, this.invitationCreatedAt, this.albumTitle);
        }
    }
}
