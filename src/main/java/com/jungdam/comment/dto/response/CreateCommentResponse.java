package com.jungdam.comment.dto.response;

public class CreateCommentResponse {

    private final Long commentId;
    private final String commentContent;
    private final String nickname;
    private final String avatar;

    public CreateCommentResponse(Long commentId, String commentContent, String nickname,
        String avatar) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public static CreateCommentResponseBuilder builder() {
        return new CreateCommentResponseBuilder();
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public static class CreateCommentResponseBuilder {

        private Long commentId;
        private String commentContent;
        private String nickname;
        private String avatar;

        public CreateCommentResponseBuilder commentId(final Long commentId) {
            this.commentId = commentId;
            return this;
        }

        public CreateCommentResponseBuilder commentContent(final String commentContent) {
            this.commentContent = commentContent;
            return this;
        }

        public CreateCommentResponseBuilder nickname(final String nickname) {
            this.nickname = nickname;
            return this;
        }

        public CreateCommentResponseBuilder avatar(final String avatar) {
            this.avatar = avatar;
            return this;
        }

        public CreateCommentResponse build() {
            return new CreateCommentResponse(this.commentId, this.commentContent,
                this.nickname, this.avatar);
        }
    }
}