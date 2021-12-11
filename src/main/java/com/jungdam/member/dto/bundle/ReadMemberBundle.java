package com.jungdam.member.dto.bundle;

public class ReadMemberBundle {

    private final Long memberId;

    public ReadMemberBundle(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
}