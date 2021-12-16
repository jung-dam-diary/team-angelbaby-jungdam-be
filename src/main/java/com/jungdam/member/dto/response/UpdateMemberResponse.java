package com.jungdam.member.dto.response;

public class UpdateMemberResponse {

    private final String nickname;
    private final String avatar;

    public UpdateMemberResponse(String nickname, String avatar) {
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
