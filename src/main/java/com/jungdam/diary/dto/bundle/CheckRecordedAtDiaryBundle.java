package com.jungdam.diary.dto.bundle;

import com.jungdam.diary.domain.vo.RecordedAt;
import java.time.LocalDate;

public class CheckRecordedAtDiaryBundle {

    private final Long memberId;
    private final Long albumId;
    private final RecordedAt recordedAt;

    public CheckRecordedAtDiaryBundle(Long memberId, Long albumId, LocalDate recordedAt) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.recordedAt = new RecordedAt(recordedAt);
    }

    public static CheckRecordedAtDiaryBundleBuilder builder() {
        return new CheckRecordedAtDiaryBundleBuilder();
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public RecordedAt getRecordedAt() {
        return recordedAt;
    }

    public static class CheckRecordedAtDiaryBundleBuilder {

        private Long memberId;
        private Long albumId;
        private LocalDate recordedAt;

        private CheckRecordedAtDiaryBundleBuilder() {
        }

        public CheckRecordedAtDiaryBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public CheckRecordedAtDiaryBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public CheckRecordedAtDiaryBundleBuilder recordedAt(final LocalDate recordedAt) {
            this.recordedAt = recordedAt;
            return this;
        }

        public CheckRecordedAtDiaryBundle build() {
            return new CheckRecordedAtDiaryBundle(this.memberId, this.albumId, this.recordedAt);
        }
    }
}
