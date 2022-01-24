package com.jungdam.stored_emoji.converter;

import com.jungdam.diary.domain.Diary;
import com.jungdam.emoji.domain.Emoji;
import com.jungdam.stored_emoji.domain.StoredEmoji;
import com.jungdam.stored_emoji.dto.response.CreateAndDeleteStoredEmojiResponse;
import com.jungdam.stored_emoji.dto.response.CreateAndDeleteStoredEmojiResponse.EmojiDetailResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class StoredEmojiConverter {


    public List<EmojiDetailResponse> toEmojiDetailResponses(List<Emoji> emojis,
        List<StoredEmoji> storedEmojis) {
        return emojis.stream()
            .map(emoji -> new EmojiDetailResponse(
                emoji.getContentValue(),
                (int) storedEmojis.stream()
                    .filter(storedEmoji -> storedEmoji.isEqual(emoji))
                    .count()
            )).collect(Collectors.toList());
    }

    public CreateAndDeleteStoredEmojiResponse toCreateAndDeleteStoredEmojiResponse(
        Diary diary, List<EmojiDetailResponse> response) {
        return CreateAndDeleteStoredEmojiResponse.builder()
            .albumId(diary.getAlbumValue())
            .diaryId(diary.getId())
            .emojies(response)
            .build();
    }
}