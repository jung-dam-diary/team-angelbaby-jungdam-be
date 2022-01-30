package com.jungdam.stored_emoji.infrastructure;

import com.jungdam.diary.domain.Diary;
import com.jungdam.emoji.domain.Emoji;
import com.jungdam.participant.domain.Participant;
import com.jungdam.stored_emoji.domain.StoredEmoji;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoredEmojiRepository extends JpaRepository<StoredEmoji, Long> {

    boolean existsByDiaryAndParticipantAndEmoji(Diary diary, Participant participant, Emoji emoji);

    void deleteByDiaryAndParticipantAndEmoji(Diary diary, Participant participant, Emoji emoji);

    List<StoredEmoji> findByDiary(Diary diary);

    List<StoredEmoji> findByDiaryAndEmoji(Diary diary, Emoji emoji);
}