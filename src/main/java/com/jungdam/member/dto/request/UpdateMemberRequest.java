package com.jungdam.member.dto.request;

public class UpdateMemberRequest {

    private final String nickname;
    private final String avatar;

    public UpdateMemberRequest(String nickname, String avatar) {
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatar() {
        return avatar;
    }
}
