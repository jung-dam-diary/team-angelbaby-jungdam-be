package com.jungdam.participant.dto.response;

public class ParticipantInfoResponse {

    private final String nickname;
    private final String avatar;

    public ParticipantInfoResponse(String nickname, String avatar) {
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