package com.jungdam.stored_emoji.dto.response;

import java.util.List;

public class InquireEmojiResponse {

    private final String emoji;
    private final List<String> nicknames;

    public InquireEmojiResponse(String emoji, List<String> nicknames) {
        this.emoji = emoji;
        this.nicknames = nicknames;
    }

    public String getEmoji() {
        return emoji;
    }

    public List<String> getNicknames() {
        return nicknames;
    }
}