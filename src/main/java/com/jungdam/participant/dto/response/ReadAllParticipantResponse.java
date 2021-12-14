package com.jungdam.participant.dto.response;

import java.util.List;

public class ReadAllParticipantResponse {

    private final List<ReadParticipantResponse> participants;

    public ReadAllParticipantResponse(
        List<ReadParticipantResponse> participants) {
        this.participants = participants;
    }

    public List<ReadParticipantResponse> getParticipants() {
        return participants;
    }
}
