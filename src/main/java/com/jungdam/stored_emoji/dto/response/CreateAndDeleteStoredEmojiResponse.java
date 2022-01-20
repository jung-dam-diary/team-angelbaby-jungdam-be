package com.jungdam.stored_emoji.dto.response;

import java.util.List;

public class CreateAndDeleteStoredEmojiResponse {

    private final Long albumId;
    private final Long diaryId;
    private final List<Emojies> emojies;

    public CreateAndDeleteStoredEmojiResponse(Long albumId, Long diaryId,
        List<Emojies> emojies) {
        this.albumId = albumId;
        this.diaryId = diaryId;
        this.emojies = emojies;
    }

    public Long getAlbumId() {
        return albumId;
    }

    private static class Emojies {

        private final String content;
        private final String number;
        private final List<String> nickname;

        public Emojies(String content, String number, List<String> nickname) {
            this.content = content;
            this.number = number;
            this.nickname = nickname;
        }

        public String getContent() {
            return content;
        }

        public String getNumber() {
            return number;
        }

        public List<String> getNickname() {
            return nickname;
        }
    }
}