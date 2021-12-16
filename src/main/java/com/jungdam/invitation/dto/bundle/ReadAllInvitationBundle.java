package com.jungdam.invitation.dto.bundle;

public class ReadAllInvitationBundle {

    private final Long memberId;

    public ReadAllInvitationBundle(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
