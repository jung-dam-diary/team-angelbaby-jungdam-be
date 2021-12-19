package com.jungdam.diary.dto.response;

import java.time.LocalDate;
import java.util.List;

public class DiaryInfoResponse {

    private final LocalDate recordedAt;
    private final Long diaryId;
    private final String title;
    private final boolean bookmark;
    private final List<String> diaryPhotos;

    public DiaryInfoResponse(LocalDate recordedAt, Long diaryId, String title, boolean bookmark,
        List<String> diaryPhotos) {
        this.recordedAt = recordedAt;
        this.diaryId = diaryId;
        this.title = title;
        this.bookmark = bookmark;
        this.diaryPhotos = diaryPhotos;
    }

    public static DiaryInfoResponseBuilder builder() {
        return new DiaryInfoResponseBuilder();
    }

    public LocalDate getRecordedAt() {
        return recordedAt;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBookmark() {
        return bookmark;
    }

    public List<String> getDiaryPhotos() {
        return diaryPhotos;
    }

    public static class DiaryInfoResponseBuilder {

        private LocalDate recordedAt;
        private Long diaryId;
        private String title;
        private boolean bookmark;
        private List<String> diaryPhotos;

        private DiaryInfoResponseBuilder() {
        }

        public DiaryInfoResponseBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public DiaryInfoResponseBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public DiaryInfoResponseBuilder bookmark(final boolean bookmark) {
            this.bookmark = bookmark;
            return this;
        }

        public DiaryInfoResponseBuilder diaryPhotos(final List<String> diaryPhotos) {
            this.diaryPhotos = diaryPhotos;
            return this;
        }

        public DiaryInfoResponseBuilder recordedAt(final LocalDate recordedAt) {
            this.recordedAt = recordedAt;
            return this;
        }

        public DiaryInfoResponse build() {
            return new DiaryInfoResponse(this.recordedAt, this.diaryId, this.title, this.bookmark,
                this.diaryPhotos);
        }
    }
}