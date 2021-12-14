package com.jungdam.diary.dto.bundle;

public class ReadDiaryBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long diaryId;

    private ReadDiaryBundle(Long memberId, Long albumId, Long diaryId) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.diaryId = diaryId;
    }

    public static ReadDiaryBundleBuilder builder() {
        return new ReadDiaryBundleBuilder();
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

    public static class ReadDiaryBundleBuilder {

        private Long memberId;
        private Long albumId;
        private Long diaryId;

        private ReadDiaryBundleBuilder() {

        }

        public ReadDiaryBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public ReadDiaryBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public ReadDiaryBundleBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public ReadDiaryBundle build() {
            return new ReadDiaryBundle(this.memberId, this.albumId, this.diaryId);
        }
    }
}