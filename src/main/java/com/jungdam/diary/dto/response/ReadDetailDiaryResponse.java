package com.jungdam.diary.dto.response;

import com.jungdam.participant.dto.response.ParticipantInfosResponse;
import java.time.LocalDate;
import java.util.List;

public class ReadDetailDiaryResponse {

    private final Long albumId;
    private final Long diaryId;
    private final String title;
    private final String content;
    private final boolean bookmark;
    private final List<String> diaryPhotos;
    private final LocalDate recordedAt;
    private final ParticipantInfosResponse participant;

    public ReadDetailDiaryResponse(Long albumId, Long diaryId, String title, String content,
        boolean bookmark,
        List<String> diaryPhotos, LocalDate recordedAt, ParticipantInfosResponse participant) {
        this.albumId = albumId;
        this.diaryId = diaryId;
        this.title = title;
        this.content = content;
        this.bookmark = bookmark;
        this.diaryPhotos = diaryPhotos;
        this.recordedAt = recordedAt;
        this.participant = participant;
    }

    public static ReadDetailDiaryResponseBuilder builder() {
        return new ReadDetailDiaryResponseBuilder();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isBookmark() {
        return bookmark;
    }

    public List<String> getDiaryPhotos() {
        return diaryPhotos;
    }

    public LocalDate getRecordedAt() {
        return recordedAt;
    }

    public ParticipantInfosResponse getParticipant() {
        return participant;
    }

    public static class ReadDetailDiaryResponseBuilder {

        private Long albumId;
        private Long diaryId;
        private String title;
        private String content;
        private boolean bookmark;
        private List<String> diaryPhotos;
        private LocalDate recordedAt;
        private ParticipantInfosResponse info;

        private ReadDetailDiaryResponseBuilder() {

        }

        public ReadDetailDiaryResponseBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public ReadDetailDiaryResponseBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public ReadDetailDiaryResponseBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public ReadDetailDiaryResponseBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public ReadDetailDiaryResponseBuilder bookmark(final boolean bookmark) {
            this.bookmark = bookmark;
            return this;
        }

        public ReadDetailDiaryResponseBuilder diaryPhotos(final List<String> diaryPhotos) {
            this.diaryPhotos = diaryPhotos;
            return this;
        }

        public ReadDetailDiaryResponseBuilder recordedAt(final LocalDate recordedAt) {
            this.recordedAt = recordedAt;
            return this;
        }

        public ReadDetailDiaryResponseBuilder info(final ParticipantInfosResponse info) {
            this.info = info;
            return this;
        }

        public ReadDetailDiaryResponse build() {
            return new ReadDetailDiaryResponse(this.albumId, this.albumId, this.title, this.content,
                this.bookmark, this.diaryPhotos, this.recordedAt, this.info);
        }
    }
}