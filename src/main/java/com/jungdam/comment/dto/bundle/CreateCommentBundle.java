package com.jungdam.comment.dto.bundle;

import com.jungdam.comment.domain.vo.Content;
import com.jungdam.comment.dto.request.CreateCommentRequest;

public class CreateCommentBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long diaryId;
    private final Content content;

    public CreateCommentBundle(Long memberId, Long albumId, Long diaryId,
        CreateCommentRequest request) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.diaryId = diaryId;
        this.content = new Content(request.getCommentContent());
    }

    public static CreateCommentBundleBuilder build() {
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

    public Content getContent() {
        return content;
    }

    public static class CreateCommentBundleBuilder {

        private Long memberId;
        private Long albumId;
        private Long diaryId;
        private CreateCommentRequest request;

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

        public CreateCommentBundleBuilder request(final CreateCommentRequest request) {
            this.request = request;
            return this;
        }

        public CreateCommentBundle build() {
            return new CreateCommentBundle(this.memberId, this.albumId, this.diaryId, this.request);
        }
    }
}