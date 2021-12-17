package com.jungdam.comment.dto.response;

public class ReadCommentResponse {

    private final Long commentId;
    private final String commentContent;
    private final String nickname;
    private final String avatar;

    public ReadCommentResponse(Long commentId, String commentContent, String nickname,
        String avatar) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public static ReadCommentResponseBuilder builder() {
        return new ReadCommentResponseBuilder();
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

    public static class ReadCommentResponseBuilder {

        private Long commentId;
        private String commentContent;
        private String nickname;
        private String avatar;

        private ReadCommentResponseBuilder() {
        }

        public ReadCommentResponseBuilder commentId(final Long commentId) {
            this.commentId = commentId;
            return this;
        }

        public ReadCommentResponseBuilder commentContent(final String commentContent) {
            this.commentContent = commentContent;
            return this;
        }

        public ReadCommentResponseBuilder nickname(final String nickname) {
            this.nickname = nickname;
            return this;
        }

        public ReadCommentResponseBuilder avatar(final String avatar) {
            this.avatar = avatar;
            return this;
        }

        public ReadCommentResponse build() {
            return new ReadCommentResponse(this.commentId, this.commentContent, this.nickname,
                this.avatar);
        }
    }
}