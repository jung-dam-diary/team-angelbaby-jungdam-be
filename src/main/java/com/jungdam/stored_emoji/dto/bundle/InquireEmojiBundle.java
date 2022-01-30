package com.jungdam.stored_emoji.dto.bundle;

import com.jungdam.emoji.domain.vo.Content;

public class InquireEmojiBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long diaryId;
    private final Content content;

    private InquireEmojiBundle(Long memberId, Long albumId, Long diaryId,
        String q) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.diaryId = diaryId;
        this.content = new Content(q);
    }

    public static InquireEmojiBundleBuilder builder() {
        return new InquireEmojiBundleBuilder();
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

    public static class InquireEmojiBundleBuilder {

        private Long memberId;
        private Long albumId;
        private Long diaryId;
        private String content;

        private InquireEmojiBundleBuilder() {
        }

        public InquireEmojiBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public InquireEmojiBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public InquireEmojiBundleBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public InquireEmojiBundleBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public InquireEmojiBundle build() {
            return new InquireEmojiBundle(memberId, albumId, diaryId, content);
        }
    }
}