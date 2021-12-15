package com.jungdam.comment.dto.request;

public class CreateCommentRequest {

    private String commentContent;

    protected CreateCommentRequest() {
    }

    public String getCommentContent() {
        return commentContent;
    }
}