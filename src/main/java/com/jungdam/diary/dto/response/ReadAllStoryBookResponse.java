package com.jungdam.diary.dto.response;

import java.util.List;

public class ReadAllStoryBookResponse {

    private final boolean hasNext;
    private final Long participantId;
    private final String participantNickname;
    private final String participantAvatar;
    private final List<ReadStoryBookResponse> diaries;

    public ReadAllStoryBookResponse(boolean hasNext, Long participantId, String participantNickname,
        String participantAvatar, List<ReadStoryBookResponse> diaries) {
        this.hasNext = hasNext;
        this.participantId = participantId;
        this.participantNickname = participantNickname;
        this.participantAvatar = participantAvatar;
        this.diaries = diaries;
    }

    public static ReadAllStoryBookResponseBuilder builder() {
        return new ReadAllStoryBookResponseBuilder();
    }

    public boolean isHasNext() {
        return hasNext;
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

    public static class ReadAllStoryBookResponseBuilder {

        private boolean hasNext;
        private Long participantId;
        private String participantNickname;
        private String participantAvatar;
        private List<ReadStoryBookResponse> diaries;

        private ReadAllStoryBookResponseBuilder() {
        }

        public ReadAllStoryBookResponseBuilder hasNext(final boolean hasNext) {
            this.hasNext = hasNext;
            return this;
        }

        public ReadAllStoryBookResponseBuilder participantId(final Long participantId) {
            this.participantId = participantId;
            return this;
        }

        public ReadAllStoryBookResponseBuilder participantNickname(
            final String participantNickname) {
            this.participantNickname = participantNickname;
            return this;
        }

        public ReadAllStoryBookResponseBuilder participantAvatar(final String participantAvatar) {
            this.participantAvatar = participantAvatar;
            return this;
        }

        public ReadAllStoryBookResponseBuilder diaries(final List<ReadStoryBookResponse> diaries) {
            this.diaries = diaries;
            return this;
        }

        public ReadAllStoryBookResponse build() {
            return new ReadAllStoryBookResponse(this.hasNext, this.participantId,
                this.participantNickname,
                this.participantAvatar, this.diaries);
        }
    }
}
