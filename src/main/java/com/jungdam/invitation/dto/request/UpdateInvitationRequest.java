package com.jungdam.invitation.dto.request;

import com.jungdam.invitation.domain.vo.Status;

public class UpdateInvitationRequest {

    private Status status;

    protected UpdateInvitationRequest() {
    }

    public Status getStatus() {
        return status;
    }
}
