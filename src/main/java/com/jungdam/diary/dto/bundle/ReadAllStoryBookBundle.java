package com.jungdam.diary.dto.bundle;

public class ReadAllStoryBookBundle {

    private final Long albumId;
    private final Long participantId;
    private final Long cursorId;
    private final Integer pageSize;

    public ReadAllStoryBookBundle(Long albumId, Long participantId, Long cursorId, Integer pageSize) {
        this.albumId = albumId;
        this.participantId = participantId;
        this.cursorId = cursorId;
        this.pageSize = pageSize;
    }

    public static ReadAllStoryBookBundleBuilder builder() {
        return new ReadAllStoryBookBundleBuilder();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public Long getCursorId() {
        return cursorId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public static class ReadAllStoryBookBundleBuilder {

        private Long albumId;
        private Long participantId;
        private Long cursorId;
        private Integer pageSize;

        private ReadAllStoryBookBundleBuilder() {
        }

        public ReadAllStoryBookBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public ReadAllStoryBookBundleBuilder participantId(final Long participantId) {
            this.participantId = participantId;
            return this;
        }

        public ReadAllStoryBookBundleBuilder cursorId(final Long cursorId) {
            this.cursorId = cursorId;
            return this;
        }

        public ReadAllStoryBookBundleBuilder pageSize(final Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ReadAllStoryBookBundle build() {
            return new ReadAllStoryBookBundle(this.albumId, this.participantId, this.cursorId, this.pageSize);
        }
    }
}
