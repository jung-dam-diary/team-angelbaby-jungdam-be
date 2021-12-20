package com.jungdam.diary.dto.response;

import java.util.List;

public class ReadGroupStoryBookResponse {

    private final Long participantId;
    private final String participantNickname;
    private final String participantAvatar;
    private final List<ReadStoryBookResponse> diaries;

    public ReadGroupStoryBookResponse(Long participantId, String participantNickname,
        String participantAvatar, List<ReadStoryBookResponse> diaries) {
        this.participantId = participantId;
        this.participantNickname = participantNickname;
        this.participantAvatar = participantAvatar;
        this.diaries = diaries;
    }

    public static ReadParticipantStoryBookResponseBuilder builder() {
        return new ReadParticipantStoryBookResponseBuilder();
    }

    public Long getParticipantId() {
        return participantId;
    }

    public String getParticipantNickname() {
        return participantNickname;
    }

    public String getParticipantAvatar() {
        return participantAvatar;
    }

    public List<ReadStoryBookResponse> getDiaries() {
        return diaries;
    }

    public static class ReadParticipantStoryBookResponseBuilder {

        private Long participantId;
        private String participantNickname;
        private String participantAvatar;
        private List<ReadStoryBookResponse> diaries;

        public ReadParticipantStoryBookResponseBuilder participantId(final Long participantId) {
            this.participantId = participantId;
            return this;
        }

        public ReadParticipantStoryBookResponseBuilder participantNickname(final String participantNickname) {
            this.participantNickname = participantNickname;
            return this;
        }

        public ReadParticipantStoryBookResponseBuilder participantAvatar(final String participantAvatar) {
            this.participantAvatar = participantAvatar;
            return this;
        }

        public ReadParticipantStoryBookResponseBuilder diaries(final List<ReadStoryBookResponse> diaries) {
            this.diaries = diaries;
            return this;
        }

        public ReadGroupStoryBookResponse build() {
            return new ReadGroupStoryBookResponse(this.participantId, this.participantNickname,
                this.participantAvatar, this.diaries);
        }
    }
}
