package com.jungdam.diary.dto.bundle;

import com.jungdam.diary.domain.vo.Content;
import com.jungdam.diary.domain.vo.Title;
import com.jungdam.diary.dto.request.UpdateDiaryRequest;
import com.jungdam.diary_photo.domain.DiaryPhoto;
import com.jungdam.diary_photo.domain.vo.Image;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateDiaryBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long diaryId;
    private final Title title;
    private final Content content;
    private final List<DiaryPhoto> diaryPhotos;

    private UpdateDiaryBundle(Long memberId, Long albumId, Long diaryId,
        UpdateDiaryRequest request) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.diaryId = diaryId;
        this.title = new Title(request.getDiaryTitle());
        this.content = new Content(request.getDiaryContent());
        this.diaryPhotos = convert(request.getDiaryPhotos());
    }

    public static UpdateDiaryBundleBuilder builder() {
        return new UpdateDiaryBundleBuilder();
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

    public Long getDiaryId() {
        return diaryId;
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

    public static class UpdateDiaryBundleBuilder {

        private Long memberId;
        private Long albumId;
        private Long diaryId;
        private UpdateDiaryRequest request;

        private UpdateDiaryBundleBuilder() {
        }

        public UpdateDiaryBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public UpdateDiaryBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public UpdateDiaryBundleBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public UpdateDiaryBundleBuilder request(
            final UpdateDiaryRequest request) {
            this.request = request;
            return this;
        }

        public UpdateDiaryBundle build() {
            return new UpdateDiaryBundle(this.memberId, this.albumId, this.diaryId, this.request);
        }
    }
}