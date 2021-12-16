package com.jungdam.invitation.dto.bundle;

import com.jungdam.invitation.domain.vo.Status;
import com.jungdam.invitation.dto.request.UpdateInvitationRequest;

public class UpdateInvitationBundle {

    private final Long memberId;
    private final Long invitationId;
    private final Status status;

    public UpdateInvitationBundle(Long memberId, Long invitationId, UpdateInvitationRequest request) {
        this.memberId = memberId;
        this.invitationId = invitationId;
        this.status = Status.from(request.getStatus());
    }

    public static UpdateInvitationResponseBuilder builder() {
        return new UpdateInvitationResponseBuilder();
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public Status getStatus() {
        return status;
    }

    public static class UpdateInvitationResponseBuilder {

        private Long memberId;
        private Long invitationId;
        private UpdateInvitationRequest request;

        private UpdateInvitationResponseBuilder() {
        }

        public UpdateInvitationResponseBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public UpdateInvitationResponseBuilder invitationId(final Long invitationId) {
            this.invitationId = invitationId;
            return this;
        }

        public UpdateInvitationResponseBuilder request(final UpdateInvitationRequest request) {
            this.request = request;
            return this;
        }

        public UpdateInvitationBundle build() {
            return new UpdateInvitationBundle(this.memberId, this.invitationId, this.request);
        }
    }

}
