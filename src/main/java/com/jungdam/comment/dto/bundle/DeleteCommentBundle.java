package com.jungdam.comment.dto.bundle;

public class DeleteCommentBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long diaryId;
    private final Long commentId;

    public DeleteCommentBundle(Long memberId, Long albumId, Long diaryId, Long commentId) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.diaryId = diaryId;
        this.commentId = commentId;
    }

    public static DeleteCommentBundleBuilder builder() {
        return new DeleteCommentBundleBuilder();
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

    public Long getCommentId() {
        return commentId;
    }

    public static class DeleteCommentBundleBuilder {

        private Long memberId;
        private Long albumId;
        private Long diaryId;
        private Long commentId;

        private DeleteCommentBundleBuilder() {
        }

        public DeleteCommentBundle build() {
            return new DeleteCommentBundle(this.memberId, this.albumId, this.diaryId,
                this.commentId);
        }

        public DeleteCommentBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public DeleteCommentBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public DeleteCommentBundleBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public DeleteCommentBundleBuilder commentId(final Long commentId) {
            this.commentId = commentId;
            return this;
        }
    }
}