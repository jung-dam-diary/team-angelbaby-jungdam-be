package com.jungdam.diary.dto.bundle;

public class CheckBookmarkBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long diaryId;

    public CheckBookmarkBundle(Long memberId, Long albumId, Long diaryId) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.diaryId = diaryId;
    }

    public static CheckBookmarkBundleBuilder builder() {
        return new CheckBookmarkBundleBuilder();
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

    public static class CheckBookmarkBundleBuilder {

        private Long memberId;
        private Long albumId;
        private Long diaryId;

        private CheckBookmarkBundleBuilder() {
        }

        public CheckBookmarkBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public CheckBookmarkBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public CheckBookmarkBundleBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public CheckBookmarkBundle build() {
            return new CheckBookmarkBundle(this.memberId, this.albumId, this.diaryId);
        }
    }
}