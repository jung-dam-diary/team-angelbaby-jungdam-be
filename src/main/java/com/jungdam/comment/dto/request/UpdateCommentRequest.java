package com.jungdam.comment.dto.request;

public class UpdateCommentRequest {

    private String commentContent;

    protected UpdateCommentRequest() {
    }

    public String getCommentContent() {
        return commentContent;
    }
}