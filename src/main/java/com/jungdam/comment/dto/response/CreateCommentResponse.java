package com.jungdam.comment.dto.response;

public class CreateCommentResponse {

    private final Long diaryId;
    private final String commentContent;

    public CreateCommentResponse(Long diaryId, String commentContent) {
        this.diaryId = diaryId;
        this.commentContent = commentContent;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public String getCommentContent() {
        return commentContent;
    }
}