package com.jungdam.stored_emoji.presenatation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import com.jungdam.stored_emoji.dto.bundle.CreateAndDeleteStoredEmojiBundle;
import com.jungdam.stored_emoji.dto.request.CreateAndDeleteStoredEmojiRequest;
import com.jungdam.stored_emoji.dto.response.CreateAndDeleteStoredEmojiResponse;
import com.jungdam.stored_emoji.facade.StoredEmojiFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Stored Emoji")
@RestController
@RequestMapping("/api/v1/albums/{albumId}/diaries/{diaryId}/stored-emojis")
public class StoredEmojiController {

    private final StoredEmojiFacade storedEmojiFacade;

    public StoredEmojiController(StoredEmojiFacade storedEmojiFacade) {
        this.storedEmojiFacade = storedEmojiFacade;
    }

    @ApiOperation("이모지 저장 및 삭제")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateAndDeleteStoredEmojiResponse>> createAndDelete(
        @PathVariable Long albumId, @PathVariable Long diaryId, @RequestBody
        CreateAndDeleteStoredEmojiRequest request) {
        Long memberId = SecurityUtils.getCurrentUsername();

        CreateAndDeleteStoredEmojiBundle bundle = CreateAndDeleteStoredEmojiBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .diaryId(diaryId)
            .content(request)
            .build();

        CreateAndDeleteStoredEmojiResponse response = storedEmojiFacade.createAndDelete(
            bundle);

        return ResponseDto.ok(ResponseMessage.EMOJI_CREATE_AND_DELETE_SUCCESS, response);
    }
}