package com.jungdam.stored_emoji.dto.request;

public class CreateAndDeleteStoredEmojiRequest {

    private String content;

    public CreateAndDeleteStoredEmojiRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
