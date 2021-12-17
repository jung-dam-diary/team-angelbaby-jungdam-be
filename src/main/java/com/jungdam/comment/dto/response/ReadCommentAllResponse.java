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

    public boolean isHasNext() {
        return hasNext;
    }

    public Long getLastCommentId() {
        return lastCommentId;
    }

    public List<ReadCommentResponse> getComments() {
        return comments;
    }
}