package com.jungdam.invitation.dto.request;

public class CreateInvitationRequest {

    private Long targetMemberId;

    protected CreateInvitationRequest() {
    }

    public Long getTargetMemberId() {
        return targetMemberId;
    }
}
