package com.jungdam.invitation.dto.response;

import com.jungdam.invitation.domain.vo.Status;

public class UpdateInvitationResponse {

    private final Long albumId;
    private final Long invitationId;
    private final Status status;

    public UpdateInvitationResponse(Long albumId, Long id,
        Status status) {
        this.albumId = albumId;
        this.invitationId = id;
        this.status = status;
    }

    public static UpdateInvitationResponseBuilder builder() {
        return new UpdateInvitationResponseBuilder();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public Status getStatus() {
        return status;
    }

    public static class UpdateInvitationResponseBuilder {

        private Long albumId;
        private Long invitationId;
        private Status status;

        private UpdateInvitationResponseBuilder() {
        }

        public UpdateInvitationResponseBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public UpdateInvitationResponseBuilder invitationId(final Long invitationId) {
            this.invitationId = invitationId;
            return this;
        }

        public UpdateInvitationResponseBuilder status(final Status status) {
            this.status = status;
            return this;
        }

        public UpdateInvitationResponse build() {
            return new UpdateInvitationResponse(this.albumId, this.invitationId, this.status);
        }
    }
}