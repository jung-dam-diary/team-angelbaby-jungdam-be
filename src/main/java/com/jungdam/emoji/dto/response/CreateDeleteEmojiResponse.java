package com.jungdam.emoji.dto.response;

public class CreateDeleteEmojiResponse {

    private final String emoji;
    private final boolean existence;

    public CreateDeleteEmojiResponse(String emoji, boolean existence) {
        this.emoji = emoji;
        this.existence = existence;
    }

    public String getEmoji() {
        return emoji;
    }

    public boolean isExistence() {
        return existence;
    }
}