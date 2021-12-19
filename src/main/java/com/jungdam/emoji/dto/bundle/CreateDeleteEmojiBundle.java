package com.jungdam.emoji.dto.bundle;

import com.jungdam.emoji.domain.vo.Content;
import com.jungdam.emoji.dto.request.CreateDeleteEmojiRequest;

public class CreateDeleteEmojiBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long diaryId;
    private final Content content;

    public CreateDeleteEmojiBundle(Long memberId, Long albumId, Long diaryId,
        CreateDeleteEmojiRequest request) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.diaryId = diaryId;
        this.content = new Content(request.getEmoji());
    }

    public static CreateDeleteEmojiBundleBuilder builder() {
        return new CreateDeleteEmojiBundleBuilder();
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

    public static class CreateDeleteEmojiBundleBuilder {

        private Long memberId;
        private Long albumId;
        private Long diaryId;
        private CreateDeleteEmojiRequest request;

        private CreateDeleteEmojiBundleBuilder() {
        }

        public CreateDeleteEmojiBundleBuilder memberId(Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public CreateDeleteEmojiBundleBuilder albumId(Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public CreateDeleteEmojiBundleBuilder diaryId(Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public CreateDeleteEmojiBundleBuilder request(CreateDeleteEmojiRequest request) {
            this.request = request;
            return this;
        }

        public CreateDeleteEmojiBundle build() {
            return new CreateDeleteEmojiBundle(this.memberId, this.albumId, this.diaryId,
                this.request);
        }
    }
}