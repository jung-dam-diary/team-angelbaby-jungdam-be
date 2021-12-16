package com.jungdam.invitation.dto.response;

import com.jungdam.invitation.domain.vo.Status;

public class UpdateInvitationResponse {

    private final Long id;
    private final Status status;

    public UpdateInvitationResponse(Long id, Status status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }
}
