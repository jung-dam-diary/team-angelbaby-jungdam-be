package com.jungdam.stored_emoji.converter;

import com.jungdam.diary.domain.Diary;
import com.jungdam.stored_emoji.domain.StoredEmoji;
import com.jungdam.stored_emoji.dto.response.CreateAndDeleteStoredEmojiResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StoredEmojiConverter {

    public CreateAndDeleteStoredEmojiResponse toCreateAndDeleteStoredEmojiResponse(
        Diary diary, List<StoredEmoji> emojis) {
        return new CreateAndDeleteStoredEmojiResponse(
            diary.getAlbumValue(),
            diary.getId(),
            null // TODO
        );
    }
}