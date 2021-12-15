package com.jungdam.participant.dto.response;

import java.util.List;

public class ReadAllParticipantResponse {

    private final List<ReadAllParticipant> participants;

    public ReadAllParticipantResponse(List<ReadAllParticipant> participants) {
        this.participants = participants;
    }

    public List<ReadAllParticipant> getParticipants() {
        return participants;
    }
}
