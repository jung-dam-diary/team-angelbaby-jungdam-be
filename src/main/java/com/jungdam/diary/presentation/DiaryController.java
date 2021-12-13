package com.jungdam.diary.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import com.jungdam.diary.application.DiaryService;
import com.jungdam.diary.dto.bundle.CreateDiaryBundle;
import com.jungdam.diary.dto.request.CreateDiaryRequest;
import com.jungdam.diary.dto.response.CreateDiaryResponse;
import com.jungdam.diary.facade.DiaryFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Diary")
@RestController
@RequestMapping("/api/v1/albums/{albumId}")
public class DiaryController {

    private final DiaryService diaryService;
    private final DiaryFacade diaryFacade;

    public DiaryController(DiaryService diaryService, DiaryFacade diaryFacade) {
        this.diaryService = diaryService;
        this.diaryFacade = diaryFacade;
    }

    @ApiOperation("일기 생성")
    @PostMapping("/diaries")
    public ResponseEntity<ResponseDto<CreateDiaryResponse>> create(
        @PathVariable Long albumId, @RequestBody CreateDiaryRequest request) {
        Long memberId = SecurityUtils.getCurrentUsername();

        CreateDiaryBundle bundle = CreateDiaryBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .request(request)
            .build();

        CreateDiaryResponse response = diaryFacade.insert(bundle);

        return ResponseDto.of(ResponseMessage.DIARY_CREATE_SUCCESS, response);
    }
}