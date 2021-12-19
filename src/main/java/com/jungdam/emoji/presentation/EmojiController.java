package com.jungdam.emoji.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import com.jungdam.emoji.dto.bundle.CreateDeleteEmojiBundle;
import com.jungdam.emoji.dto.request.CreateDeleteEmojiRequest;
import com.jungdam.emoji.dto.response.CreateDeleteEmojiResponse;
import com.jungdam.emoji.facade.EmojiFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Emoji")
@RestController
@RequestMapping("/api/v1/albums/{albumId}/diaries/{diaryId}/emojis")
public class EmojiController {

    private final EmojiFacade emojiFacade;

    public EmojiController(EmojiFacade emojiFacade) {
        this.emojiFacade = emojiFacade;
    }

    @ApiOperation("일기에 이모지 추가 및 삭제")
    @PutMapping
    public ResponseEntity<ResponseDto<CreateDeleteEmojiResponse>> register(
        @PathVariable Long albumId,
        @PathVariable Long diaryId, @RequestBody
        CreateDeleteEmojiRequest request) {
        Long memberId = SecurityUtils.getCurrentUsername();

        CreateDeleteEmojiBundle bundle = CreateDeleteEmojiBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .diaryId(diaryId)
            .request(request)
            .build();

        CreateDeleteEmojiResponse response = emojiFacade.register(bundle);

        return ResponseDto.of(ResponseMessage.EMOJI_CREATE_AND_DELETE_SUCCESS, response);
    }
}