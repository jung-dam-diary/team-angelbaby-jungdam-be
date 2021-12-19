package com.jungdam.diary.dto.response;

import com.jungdam.participant.dto.response.ParticipantInfoResponse;

public class ReadFeedDiaryResponse {

    private final DiaryInfoResponse diary;
    private final ParticipantInfoResponse participant;

    public ReadFeedDiaryResponse(DiaryInfoResponse diary,
        ParticipantInfoResponse participant) {
        this.diary = diary;
        this.participant = participant;
    }

    public DiaryInfoResponse getDiary() {
        return diary;
    }

    public ParticipantInfoResponse getParticipant() {
        return participant;
    }
}