package com.jungdam.invitation.dto.response;

public class CreateInvitationResponse {

    private final Long invitationId;

    public CreateInvitationResponse(Long invitationId) {
        this.invitationId = invitationId;
    }

    public Long getInvitationId() {
        return invitationId;
    }
}
