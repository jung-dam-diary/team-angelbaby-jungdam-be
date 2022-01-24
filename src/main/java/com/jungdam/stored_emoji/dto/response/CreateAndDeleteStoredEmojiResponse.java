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

    public Long getAlbumId() {
        return albumId;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public List<EmojiDetailResponse> getEmojies() {
        return emojies;
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