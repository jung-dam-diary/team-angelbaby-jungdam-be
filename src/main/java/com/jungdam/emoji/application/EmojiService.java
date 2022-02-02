package com.jungdam.emoji.application;

import com.jungdam.emoji.domain.Emoji;
import com.jungdam.emoji.domain.vo.Content;
import com.jungdam.emoji.infrastructure.EmojiRepository;
import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.NotExistException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmojiService {

    private final static Logger log = LoggerFactory.getLogger(EmojiService.class);

    private final EmojiRepository emojiRepository;

    public EmojiService(EmojiRepository emojiRepository) {
        this.emojiRepository = emojiRepository;
    }

    @Transactional(readOnly = true)
    public Emoji findByContent(Content content) {
        return emojiRepository.findByContent(content)
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_EMOJI).error(log));
    }

    @Transactional(readOnly = true)
    public List<Emoji> findAll() {
        return emojiRepository.findAll();
    }
}