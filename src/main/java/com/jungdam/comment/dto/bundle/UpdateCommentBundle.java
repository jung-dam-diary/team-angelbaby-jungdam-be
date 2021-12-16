package com.jungdam.comment.dto.bundle;

import com.jungdam.comment.domain.vo.Content;
import com.jungdam.comment.dto.request.UpdateCommentRequest;

public class UpdateCommentBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long diaryId;
    private final Long commentId;
    private final Content content;

    public UpdateCommentBundle(Long memberId, Long albumId, Long diaryId, Long commentId,
        UpdateCommentRequest request) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.diaryId = diaryId;
        this.commentId = commentId;
        this.content = new Content(request.getCommentContent());
    }

    public static UpdateCommentBundleBuilder builder() {
        return new UpdateCommentBundleBuilder();
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

    public Content getContent() {
        return content;
    }

    public static class UpdateCommentBundleBuilder {

        private Long memberId;
        private Long albumId;
        private Long diaryId;
        private Long commentId;
        private UpdateCommentRequest request;

        private UpdateCommentBundleBuilder() {

        }

        public UpdateCommentBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public UpdateCommentBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public UpdateCommentBundleBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public UpdateCommentBundleBuilder commentId(final Long commentId) {
            this.commentId = commentId;
            return this;
        }

        public UpdateCommentBundleBuilder request(final UpdateCommentRequest request) {
            this.request = request;
            return this;
        }

        public UpdateCommentBundle build() {
            return new UpdateCommentBundle(this.memberId, this.albumId, this.diaryId,
                this.commentId, this.request);
        }
    }
}