package com.jungdam.diary.dto.response;

import java.util.List;

public class ReadAllFeedDiaryResponse {

    private final boolean hasNext;
    private final String lastRecordedAt;
    private final List<ReadFeedDiaryResponse> diaries;

    public ReadAllFeedDiaryResponse(boolean hasNext, String lastCommentId,
        List<ReadFeedDiaryResponse> diaries) {
        this.hasNext = hasNext;
        this.lastRecordedAt = lastCommentId;
        this.diaries = diaries;
    }

    public static ReadAllFeedDiaryResponseBuilder builder() {
        return new ReadAllFeedDiaryResponseBuilder();
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public String getLastRecordedAt() {
        return lastRecordedAt;
    }

    public List<ReadFeedDiaryResponse> getDiaries() {
        return diaries;
    }

    public static class ReadAllFeedDiaryResponseBuilder {

        private boolean hasNext;
        private String lastCommentId;
        private List<ReadFeedDiaryResponse> diaries;

        private ReadAllFeedDiaryResponseBuilder() {
        }

        public ReadAllFeedDiaryResponseBuilder hasNext(final boolean hasNext) {
            this.hasNext = hasNext;
            return this;
        }

        public ReadAllFeedDiaryResponseBuilder lastCommentId(final String lastCommentId) {
            this.lastCommentId = lastCommentId;
            return this;
        }

        public ReadAllFeedDiaryResponseBuilder diaries(final List<ReadFeedDiaryResponse> diaries) {
            this.diaries = diaries;
            return this;
        }

        public ReadAllFeedDiaryResponse build() {
            return new ReadAllFeedDiaryResponse(this.hasNext, this.lastCommentId,
                this.diaries);
        }
    }
}