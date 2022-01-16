package com.jungdam.stored_emoji.dto.bundle;

import com.jungdam.emoji.domain.vo.Content;
import com.jungdam.stored_emoji.dto.request.CreateAndDeleteStoredEmojiRequest;

public class CreateAndDeleteStoredEmojiBundle {

    private final Long memberId;
    private final Long albumId;
    private final Long diaryId;
    private final Content content;

    public CreateAndDeleteStoredEmojiBundle(Long memberId, Long albumId, Long diaryId,
        CreateAndDeleteStoredEmojiRequest request) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.diaryId = diaryId;
        this.content = new Content(request.getContent());
    }

    public static CreateAndDeleteStoredEmojiBundleBuilder builder() {
        return new CreateAndDeleteStoredEmojiBundleBuilder();
    }

    public static class CreateAndDeleteStoredEmojiBundleBuilder {

        private Long memberId;
        private Long albumId;
        private Long diaryId;
        private CreateAndDeleteStoredEmojiRequest request;

        private CreateAndDeleteStoredEmojiBundleBuilder() {
        }

        public CreateAndDeleteStoredEmojiBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public CreateAndDeleteStoredEmojiBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public CreateAndDeleteStoredEmojiBundleBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public CreateAndDeleteStoredEmojiBundleBuilder content(
            final CreateAndDeleteStoredEmojiRequest request) {
            this.request = request;
            return this;
        }

        public CreateAndDeleteStoredEmojiBundle build() {
            return new CreateAndDeleteStoredEmojiBundle(memberId, albumId, diaryId, request);
        }
    }
}