package com.jungdam.diary.dto.bundle;

import com.jungdam.diary.domain.vo.Content;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.diary.domain.vo.Title;
import com.jungdam.diary.dto.request.CreateDiaryRequest;
import com.jungdam.diary_photo.domain.DiaryPhoto;
import com.jungdam.diary_photo.domain.vo.Image;
import java.util.List;
import java.util.stream.Collectors;

public class CreateDiaryBundle {

    private final Long memberId;
    private final Long albumId;
    private final Title title;
    private final Content content;
    private final List<DiaryPhoto> diaryPhotos;
    private final RecordedAt recordedAt;

    private CreateDiaryBundle(Long memberId, Long albumId, CreateDiaryRequest request) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.title = new Title(request.getDiaryTitle());
        this.content = new Content(request.getDiaryContent());
        this.diaryPhotos = convert(request.getDiaryPhotos());
        this.recordedAt = new RecordedAt(request.getRecordedAt());
    }

    public static CreateDiaryBundle.CreateDiaryBundleBuilder builder() {
        return new CreateDiaryBundle.CreateDiaryBundleBuilder();
    }

    private List<DiaryPhoto> convert(List<String> diaryPhotos) {
        return diaryPhotos.stream()
            .map(dp -> new DiaryPhoto(new Image(dp)))
            .collect(Collectors.toList());
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Title getTitle() {
        return title;
    }

    public Content getContent() {
        return content;
    }

    public List<DiaryPhoto> getDiaryPhotos() {
        return diaryPhotos;
    }

    public RecordedAt getRecordedAt() {
        return recordedAt;
    }

    public static class CreateDiaryBundleBuilder {

        private Long memberId;
        private Long albumId;
        private CreateDiaryRequest request;

        private CreateDiaryBundleBuilder() {

        }

        public CreateDiaryBundle.CreateDiaryBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public CreateDiaryBundle.CreateDiaryBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public CreateDiaryBundle.CreateDiaryBundleBuilder request(
            final CreateDiaryRequest request) {
            this.request = request;
            return this;
        }

        public CreateDiaryBundle build() {
            return new CreateDiaryBundle(this.memberId, this.albumId, this.request);
        }
    }
}