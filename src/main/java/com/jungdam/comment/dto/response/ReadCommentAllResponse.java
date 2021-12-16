package com.jungdam.comment.dto.response;

import java.util.List;

public class ReadCommentAllResponse {

    private final boolean hasNext;
    private final List<ReadCommentResponse> comments;

    public ReadCommentAllResponse(boolean hasNext,
        List<ReadCommentResponse> comments) {
        this.hasNext = hasNext;
        this.comments = comments;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public List<ReadCommentResponse> getComments() {
        return comments;
    }
}