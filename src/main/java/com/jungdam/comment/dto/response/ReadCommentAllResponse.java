package com.jungdam.comment.dto.response;

import java.util.List;

public class ReadCommentAllResponse {

    private final boolean hasNext;
    private final Long lastCommentId;
    private final List<ReadCommentResponse> comments;

    public ReadCommentAllResponse(boolean hasNext, Long lastCommentId,
        List<ReadCommentResponse> comments) {
        this.hasNext = hasNext;
        this.lastCommentId = lastCommentId;
        this.comments = comments;
    }

    public static ReadCommentAllResponseBuilder builder() {
        return new ReadCommentAllResponseBuilder();
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public Long getLastCommentId() {
        return lastCommentId;
    }

    public List<ReadCommentResponse> getComments() {
        return comments;
    }

    public static class ReadCommentAllResponseBuilder {

        private boolean hasNext;
        private Long lastCommentId;
        private List<ReadCommentResponse> comments;

        private ReadCommentAllResponseBuilder() {
        }

        public ReadCommentAllResponseBuilder hasNext(final boolean hasNext) {
            this.hasNext = hasNext;
            return this;
        }

        public ReadCommentAllResponseBuilder lastCommentId(final Long lastCommentId) {
            this.lastCommentId = lastCommentId;
            return this;
        }

        public ReadCommentAllResponseBuilder comments(final List<ReadCommentResponse> comments) {
            this.comments = comments;
            return this;
        }

        public ReadCommentAllResponse build() {
            return new ReadCommentAllResponse(this.hasNext, this.lastCommentId, this.comments);
        }
    }
}