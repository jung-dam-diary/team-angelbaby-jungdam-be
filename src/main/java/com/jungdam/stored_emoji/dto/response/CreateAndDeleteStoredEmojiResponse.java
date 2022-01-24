package com.jungdam.stored_emoji.dto.response;

import java.util.List;

public class CreateAndDeleteStoredEmojiResponse {

    private final Long albumId;
    private final Long diaryId;
    private final List<EmojiDetailResponse> emojies;

    public CreateAndDeleteStoredEmojiResponse(Long albumId, Long diaryId,
        List<EmojiDetailResponse> emojies) {
        this.albumId = albumId;
        this.diaryId = diaryId;
        this.emojies = emojies;
    }

    public static CreateAndDeleteStoredEmojiResponseBuilder builder() {
        return new CreateAndDeleteStoredEmojiResponseBuilder();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public List<EmojiDetailResponse> getEmojies() {
        return emojies;
    }

    public static class CreateAndDeleteStoredEmojiResponseBuilder {

        private Long albumId;
        private Long diaryId;
        private List<EmojiDetailResponse> emojies;

        private CreateAndDeleteStoredEmojiResponseBuilder() {
        }

        public CreateAndDeleteStoredEmojiResponseBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public CreateAndDeleteStoredEmojiResponseBuilder diaryId(final Long diaryId) {
            this.diaryId = diaryId;
            return this;
        }

        public CreateAndDeleteStoredEmojiResponseBuilder emojies(
            final List<EmojiDetailResponse> emojies) {
            this.emojies = emojies;
            return this;
        }

        public CreateAndDeleteStoredEmojiResponse build() {
            return new CreateAndDeleteStoredEmojiResponse(albumId, diaryId, emojies);
        }
    }

    public static class EmojiDetailResponse {

        private final String content;
        private final Integer number;

        public EmojiDetailResponse(String content, Integer number) {
            this.content = content;
            this.number = number;
        }

        public String getContent() {
            return content;
        }

        public Integer getNumber() {
            return number;
        }
    }
}