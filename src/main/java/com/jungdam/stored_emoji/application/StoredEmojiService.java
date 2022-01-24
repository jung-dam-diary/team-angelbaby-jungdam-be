package com.jungdam.stored_emoji.application;

import com.jungdam.diary.domain.Diary;
import com.jungdam.emoji.domain.Emoji;
import com.jungdam.participant.domain.Participant;
import com.jungdam.stored_emoji.domain.StoredEmoji;
import com.jungdam.stored_emoji.infrastructure.StoredEmojiRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoredEmojiService {

    private final StoredEmojiRepository storedEmojiRepository;

    public StoredEmojiService(
        StoredEmojiRepository storedEmojiRepository) {
        this.storedEmojiRepository = storedEmojiRepository;
    }

    @Transactional(readOnly = true)
    public boolean existsStoredEmoji(Diary diary, Participant participant, Emoji emoji) {
        return storedEmojiRepository.existsByDiaryAndParticipantAndEmoji(diary, participant, emoji);
    }

    @Transactional
    public void save(StoredEmoji storedEmoji) {
        storedEmojiRepository.save(storedEmoji);
    }

    @Transactional
    public void delete(Diary diary, Participant participant, Emoji emoji) {
        storedEmojiRepository.deleteByDiaryAndParticipantAndEmoji(diary, participant, emoji);
    }

    @Transactional(readOnly = true)
    public List<StoredEmoji> findByDiary(Diary diary) {
        return storedEmojiRepository.findByDiary(diary);
    }

}