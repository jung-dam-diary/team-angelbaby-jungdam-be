package com.jungdam.participant.dto.response;

public class UpdateNicknameParticipantResponse {

    private final Long participantId;
    private final String nickname;

    public UpdateNicknameParticipantResponse(Long participantId, String nickname) {
        this.participantId = participantId;
        this.nickname = nickname;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public String getNickname() {
        return nickname;
    }
}