package com.jungdam.diary.dto.bundle;

public class ReadAllDiaryBundle {

    private final Long memberId;
    private final Long albumId;
    private final String cursorId;
    private final Integer pageSize;

    public ReadAllDiaryBundle(Long memberId, Long albumId, String cursorId, Integer pageSize) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.cursorId = cursorId;
        this.pageSize = pageSize;
    }

    public static ReadAllDiaryBundleBuilder builder() {
        return new ReadAllDiaryBundleBuilder();
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public String getCursorId() {
        return cursorId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public static class ReadAllDiaryBundleBuilder {

        private Long memberId;
        private Long albumId;
        private String cursorId;
        private Integer pageSize;

        private ReadAllDiaryBundleBuilder() {
        }

        public ReadAllDiaryBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public ReadAllDiaryBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public ReadAllDiaryBundleBuilder cursorId(final String cursorId) {
            this.cursorId = cursorId;
            return this;
        }

        public ReadAllDiaryBundleBuilder pageSize(final Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ReadAllDiaryBundle build() {
            return new ReadAllDiaryBundle(this.memberId, this.albumId, this.cursorId,
                this.pageSize);
        }
    }
}