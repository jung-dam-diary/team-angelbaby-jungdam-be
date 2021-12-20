package com.jungdam.diary.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import com.jungdam.diary.dto.bundle.CheckBookmarkBundle;
import com.jungdam.diary.dto.bundle.CheckRecordedAtDiaryBundle;
import com.jungdam.diary.dto.bundle.CreateDiaryBundle;
import com.jungdam.diary.dto.bundle.DeleteDiaryBundle;
import com.jungdam.diary.dto.bundle.ReadAllDiaryBundle;
import com.jungdam.diary.dto.bundle.ReadAllStoryBookBundle;
import com.jungdam.diary.dto.bundle.ReadDiaryBundle;
import com.jungdam.diary.dto.bundle.ReadGroupStoryBookBundle;
import com.jungdam.diary.dto.bundle.UpdateDiaryBundle;
import com.jungdam.diary.dto.request.CreateDiaryRequest;
import com.jungdam.diary.dto.request.UpdateDiaryRequest;
import com.jungdam.diary.dto.response.CheckBookmarkResponse;
import com.jungdam.diary.dto.response.CheckRecordedAtDiaryResponse;
import com.jungdam.diary.dto.response.CreateDiaryResponse;
import com.jungdam.diary.dto.response.DeleteDiaryResponse;
import com.jungdam.diary.dto.response.ReadAllFeedDiaryResponse;
import com.jungdam.diary.dto.response.ReadAllStoryBookResponse;
import com.jungdam.diary.dto.response.ReadDetailDiaryResponse;
import com.jungdam.diary.dto.response.ReadGroupStoryBookResponse;
import com.jungdam.diary.dto.response.UpdateDiaryResponse;
import com.jungdam.diary.facade.DiaryFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("Diary")
@RestController
@RequestMapping("/api/v1/albums/{albumId}/diaries")
public class DiaryController {

    private final static int DEFAULT_PAGE_SIZE = 10;

    private final DiaryFacade diaryFacade;

    public DiaryController(DiaryFacade diaryFacade) {
        this.diaryFacade = diaryFacade;
    }

    @ApiOperation("특정 날자에 일기가 작성되었는지")
    @GetMapping("/search")
    public ResponseEntity<ResponseDto<CheckRecordedAtDiaryResponse>> check(
        @PathVariable Long albumId, @RequestParam(value = "q") String q) {
        Long memberId = SecurityUtils.getCurrentUsername();

        CheckRecordedAtDiaryBundle bundle = CheckRecordedAtDiaryBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .recordedAt(LocalDate.parse(q, DateTimeFormatter.ISO_DATE))
            .build();

        CheckRecordedAtDiaryResponse response = diaryFacade.checkRecordedAt(
            bundle);

        return ResponseDto.of(ResponseMessage.DIARY_RECORDED_AT_CHECK_SUCCESS, response);
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
    public ResponseEntity<ResponseDto<ReadDetailDiaryResponse>> read(@PathVariable Long albumId,
        @PathVariable Long diaryId) {
        Long memberId = SecurityUtils.getCurrentUsername();

        ReadDiaryBundle bundle = ReadDiaryBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .diaryId(diaryId)
            .build();

        ReadDetailDiaryResponse response = diaryFacade.find(bundle);

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

    @ApiOperation("일기 수정")
    @PutMapping("/{diaryId}")
    public ResponseEntity<ResponseDto<UpdateDiaryResponse>> update(
        @PathVariable Long albumId, @PathVariable Long diaryId,
        @RequestBody UpdateDiaryRequest request) {
        Long memberId = SecurityUtils.getCurrentUsername();

        UpdateDiaryBundle bundle = UpdateDiaryBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .diaryId(diaryId)
            .request(request)
            .build();

        UpdateDiaryResponse response = diaryFacade.update(bundle);

        return ResponseDto.of(ResponseMessage.DIARY_UPDATE_SUCCESS, response);
    }

    @ApiOperation("일기 스크롤 조회")
    @GetMapping
    public ResponseEntity<ResponseDto<ReadAllFeedDiaryResponse>> getAll(@PathVariable Long albumId,
        @RequestParam(value = "cursorId", required = false) String cursorId,
        @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        Long memberId = SecurityUtils.getCurrentUsername();

        if (Objects.isNull(pageSize)) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        ReadAllDiaryBundle bundle = ReadAllDiaryBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .cursorId(cursorId)
            .pageSize(pageSize)
            .build();

        ReadAllFeedDiaryResponse response = diaryFacade.findAll(bundle);

        return ResponseDto.of(ResponseMessage.DIARY_FEED_READ_ALL_SUCCESS, response);
    }

    @ApiOperation("스토리북 조회")
    @GetMapping("story-book/group")
    public ResponseEntity<ResponseDto<List<ReadGroupStoryBookResponse>>> getStoryBook(
        @PathVariable Long albumId) {
        Long memberId = SecurityUtils.getCurrentUsername();

        ReadGroupStoryBookBundle bundle = new ReadGroupStoryBookBundle(memberId, albumId);
        List<ReadGroupStoryBookResponse> response = diaryFacade.findStoryBook(bundle);

        return ResponseDto.of(ResponseMessage.STORY_BOOK_READ_SUCCESS, response);
    }

    @ApiOperation("스토리북 더보기 조회")
    @GetMapping("story-book")
    public ResponseEntity<ResponseDto<ReadAllStoryBookResponse>> getAllStoryBook(
        @PathVariable Long albumId,
        @RequestParam(value = "participantId") Long participantId,
        @RequestParam(value = "cursorId", required = false) Long cursorId,
        @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        Long memberId = SecurityUtils.getCurrentUsername();

        if (Objects.isNull(pageSize)) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        ReadAllStoryBookBundle bundle = ReadAllStoryBookBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .participantId(participantId)
            .cursorId(cursorId)
            .pageSize(pageSize)
            .build();

        ReadAllStoryBookResponse response = diaryFacade.findAllStoryBook(bundle);

        return ResponseDto.of(ResponseMessage.STORY_BOOK_READ_ALL_SUCCESS, response);
    }
}