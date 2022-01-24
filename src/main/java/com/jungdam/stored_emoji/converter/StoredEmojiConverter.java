package com.jungdam.stored_emoji.converter;

import com.jungdam.diary.domain.Diary;
import com.jungdam.stored_emoji.dto.response.CreateAndDeleteStoredEmojiResponse;
import com.jungdam.stored_emoji.dto.response.CreateAndDeleteStoredEmojiResponse.EmojiDetailResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StoredEmojiConverter {

    public CreateAndDeleteStoredEmojiResponse toCreateAndDeleteStoredEmojiResponse(
        Diary diary, List<EmojiDetailResponse> response) {
        return new CreateAndDeleteStoredEmojiResponse(
            diary.getAlbumValue(),
            diary.getId(),
            response
        );
    }
}