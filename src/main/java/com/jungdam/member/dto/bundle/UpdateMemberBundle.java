package com.jungdam.member.dto.bundle;

import com.jungdam.member.domain.vo.Avatar;
import com.jungdam.member.domain.vo.Nickname;
import com.jungdam.member.dto.request.UpdateMemberRequest;

public class UpdateMemberBundle {

    private final Long memberId;
    private final Nickname nickname;
    private final Avatar avatar;

    public UpdateMemberBundle(Long memberId, UpdateMemberRequest request) {
        this.memberId = memberId;
        this.nickname = new Nickname(request.getNickname());
        this.avatar = new Avatar(request.getAvatar());
    }

    public Long getMemberId() {
        return memberId;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Avatar getAvatar() {
        return avatar;
    }
}
