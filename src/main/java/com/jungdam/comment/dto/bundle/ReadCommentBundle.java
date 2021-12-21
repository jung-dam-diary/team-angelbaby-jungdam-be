package com.jungdam.comment.dto.bundle;

public class ReadCommentBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long diaryId;
    private final Long cursorId;
    private final Integer pageSize;

    public ReadCommentBundle(Long memberId, Long albumId, Long diaryId, Long cursorId,
        Integer pageSize) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.diaryId = diaryId;
        this.cursorId = cursorId;
        this.pageSize = pageSize;
    }

    public static CreateCommentBundleBuilder builder() {
        return new CreateCommentBundleBuilder();
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

    public Long getCursorId() {
        return cursorId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public static class CreateCommentBundleBuilder {

        private Long memberId;
        private Long albumId;
        private Long diaryId;
        private Long cursorId;
        private Integer pageSize;

        private CreateCommentBundleBuilder() {

        }

        public CreateCommentBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public CreateCommentBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public CreateCommentBundleBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public CreateCommentBundleBuilder cusorId(final Long cusorId) {
            this.cursorId = cusorId;
            return this;
        }

        public CreateCommentBundleBuilder pageSize(final Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ReadCommentBundle build() {
            return new ReadCommentBundle(this.memberId, this.albumId, this.diaryId, this.cursorId,
                this.pageSize);
        }
    }
}