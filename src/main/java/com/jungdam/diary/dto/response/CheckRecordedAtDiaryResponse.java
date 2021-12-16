package com.jungdam.diary.dto.response;

import java.time.LocalDate;

public class CheckRecordedAtDiaryResponse {

    private final Long albumId;
    private final LocalDate recordedAt;
    private final boolean existence;

    public CheckRecordedAtDiaryResponse(Long albumId, LocalDate recordedAt, boolean existence) {
        this.albumId = albumId;
        this.recordedAt = recordedAt;
        this.existence = existence;
    }

    public static CheckRecordedAtDiaryResponseBuilder builder() {
        return new CheckRecordedAtDiaryResponseBuilder();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public LocalDate getRecordedAt() {
        return recordedAt;
    }

    public boolean isExistence() {
        return existence;
    }

    public static class CheckRecordedAtDiaryResponseBuilder {

        private Long albumId;
        private LocalDate recordedAt;
        private boolean existence;

        private CheckRecordedAtDiaryResponseBuilder() {
        }

        public CheckRecordedAtDiaryResponseBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public CheckRecordedAtDiaryResponseBuilder recordedAt(final LocalDate recordedAt) {
            this.recordedAt = recordedAt;
            return this;
        }

        public CheckRecordedAtDiaryResponseBuilder existence(final boolean existence) {
            this.existence = existence;
            return this;
        }

        public CheckRecordedAtDiaryResponse build() {
            return new CheckRecordedAtDiaryResponse(this.albumId, this.recordedAt, this.existence);
        }
    }
}
