package com.jungdam.diary.dto.response;

import com.jungdam.diary.domain.vo.Bookmark;
import com.jungdam.diary.domain.vo.Content;
import com.jungdam.diary.domain.vo.DiaryPhotos;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.diary.domain.vo.Title;
import java.time.LocalDate;
import java.util.List;

public class ReadDiaryResponse {

    private final Long albumId;
    private final Long diaryId;
    private final String title;
    private final String content;
    private final boolean bookmark;
    private final List<String> diaryPhotos;
    private final LocalDate recordedAt;

    public ReadDiaryResponse(Long albumId, Long diaryId, Title title, Content content,
        Bookmark bookmark, DiaryPhotos diaryPhotos, RecordedAt recordedAt) {
        this.albumId = albumId;
        this.diaryId = diaryId;
        this.title = title.getTitle();
        this.content = content.getContent();
        this.bookmark = bookmark.getBookmark();
        this.diaryPhotos = diaryPhotos.getDiaryPhotosUrl();
        this.recordedAt = recordedAt.getRecordedAt();
    }

    public static ReadDiaryResponseBuilder builder() {
        return new ReadDiaryResponseBuilder();
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

    public static class ReadDiaryResponseBuilder {

        private Long albumId;
        private Long diaryId;
        private Title title;
        private Content content;
        private Bookmark bookmark;
        private DiaryPhotos diaryPhotos;
        private RecordedAt recordedAt;

        private ReadDiaryResponseBuilder() {

        }

        public ReadDiaryResponseBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public ReadDiaryResponseBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public ReadDiaryResponseBuilder title(final Title title) {
            this.title = title;
            return this;
        }

        public ReadDiaryResponseBuilder content(final Content content) {
            this.content = content;
            return this;
        }

        public ReadDiaryResponseBuilder bookmark(final Bookmark bookmark) {
            this.bookmark = bookmark;
            return this;
        }

        public ReadDiaryResponseBuilder diaryPhotos(final DiaryPhotos diaryPhotos) {
            this.diaryPhotos = diaryPhotos;
            return this;
        }

        public ReadDiaryResponseBuilder recordedAt(final RecordedAt recordedAt) {
            this.recordedAt = recordedAt;
            return this;
        }

        public ReadDiaryResponse build() {
            return new ReadDiaryResponse(this.albumId, this.albumId, this.title, this.content,
                this.bookmark, this.diaryPhotos, this.recordedAt);
        }
    }
}