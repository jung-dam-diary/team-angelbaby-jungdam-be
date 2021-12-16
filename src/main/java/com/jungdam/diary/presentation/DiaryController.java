package com.jungdam.diary.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import com.jungdam.diary.dto.bundle.CheckBookmarkBundle;
import com.jungdam.diary.dto.bundle.CreateDiaryBundle;
import com.jungdam.diary.dto.bundle.DeleteDiaryBundle;
import com.jungdam.diary.dto.bundle.ReadDiaryBundle;
import com.jungdam.diary.dto.request.CreateDiaryRequest;
import com.jungdam.diary.dto.response.CheckBookmarkResponse;
import com.jungdam.diary.dto.response.CreateDiaryResponse;
import com.jungdam.diary.dto.response.DeleteDiaryResponse;
import com.jungdam.diary.dto.response.ReadDiaryResponse;
import com.jungdam.diary.facade.DiaryFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Diary")
@RestController
@RequestMapping("/api/v1/albums/{albumId}/diaries")
public class DiaryController {

    private final DiaryFacade diaryFacade;

    public DiaryController(DiaryFacade diaryFacade) {
        this.diaryFacade = diaryFacade;
    }

    @ApiOperation("일기 생성")
    @PostMapping
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

    @ApiOperation("일기 조회")
    @GetMapping("/{diaryId}")
    public ResponseEntity<ResponseDto<ReadDiaryResponse>> read(@PathVariable Long albumId,
        @PathVariable Long diaryId) {
        Long memberId = SecurityUtils.getCurrentUsername();

        ReadDiaryBundle bundle = ReadDiaryBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .diaryId(diaryId)
            .build();

        ReadDiaryResponse response = diaryFacade.find(bundle);

        return ResponseDto.of(ResponseMessage.DIARY_READ_SUCCESS, response);
    }

    @ApiOperation("북마크 생성/삭제")
    @PutMapping("/{diaryId}/bookmark")
    public ResponseEntity<ResponseDto<CheckBookmarkResponse>> markBookmark(
        @PathVariable Long albumId,
        @PathVariable Long diaryId) {
        Long memberId = SecurityUtils.getCurrentUsername();

        CheckBookmarkBundle bundle = CheckBookmarkBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .diaryId(diaryId)
            .build();

        CheckBookmarkResponse response = diaryFacade.mark(bundle);

        return ResponseDto.of(ResponseMessage.BOOKMARK_MARK_SUCCESS, response);
    }

    @ApiOperation("일기 삭제")
    @DeleteMapping("/{diaryId}")
    public ResponseEntity<ResponseDto<DeleteDiaryResponse>> delete(@PathVariable Long albumId,
        @PathVariable Long diaryId) {
        Long memberId = SecurityUtils.getCurrentUsername();

        DeleteDiaryBundle bundle = DeleteDiaryBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .diaryId(diaryId)
            .build();

        DeleteDiaryResponse response = diaryFacade.delete(bundle);

        return ResponseDto.of(ResponseMessage.DIARY_DELETE_SUCCESS, response);
    }
}