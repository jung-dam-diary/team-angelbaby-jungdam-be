package com.jungdam.diary.dto.bundle;

public class DeleteDiaryBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long diaryId;

    private DeleteDiaryBundle(Long memberId, Long albumId, Long diaryId) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.diaryId = diaryId;
    }

    public static DeleteDiaryBundleBuilder builder() {
        return new DeleteDiaryBundleBuilder();
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

    public static class DeleteDiaryBundleBuilder {

        private Long memberId;
        private Long albumId;
        private Long diaryId;

        private DeleteDiaryBundleBuilder() {

        }

        public DeleteDiaryBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public DeleteDiaryBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public DeleteDiaryBundleBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public DeleteDiaryBundle build() {
            return new DeleteDiaryBundle(this.memberId, this.albumId, this.diaryId);
        }
    }
}