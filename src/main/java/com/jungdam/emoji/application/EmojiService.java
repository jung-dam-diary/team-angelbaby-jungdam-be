package com.jungdam.emoji.application;

import com.jungdam.emoji.domain.Emoji;
import com.jungdam.emoji.domain.vo.Content;
import com.jungdam.emoji.infrastructure.EmojiRepository;
import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.NotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmojiService {

    private final EmojiRepository emojiRepository;

    public EmojiService(EmojiRepository emojiRepository) {
        this.emojiRepository = emojiRepository;
    }

    @Transactional(readOnly = true)
    public Emoji findByContent(Content content) {
        return emojiRepository.findByContent(content)
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_EMOJI));
    }
}