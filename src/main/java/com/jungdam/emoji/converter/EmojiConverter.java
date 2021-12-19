package com.jungdam.emoji.converter;

import com.jungdam.emoji.domain.vo.Content;
import com.jungdam.emoji.dto.response.CreateDeleteEmojiResponse;
import org.springframework.stereotype.Component;

@Component
public class EmojiConverter {

    public CreateDeleteEmojiResponse toCreateDeleteEmojiResponse(Content content, boolean status) {
        return new CreateDeleteEmojiResponse(content.getContent(), status);
    }
}