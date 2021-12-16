package com.jungdam.comment.dto.response;

public class UpdateCommentResponse {

    private final Long diaryId;
    private final String commentContent;

    public UpdateCommentResponse(Long diaryId, String commentContent) {
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