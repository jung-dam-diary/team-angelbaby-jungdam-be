package com.jungdam.comment.presentation;

import com.jungdam.comment.dto.bundle.CreateCommentBundle;
import com.jungdam.comment.dto.bundle.DeleteCommentBundle;
import com.jungdam.comment.dto.bundle.ReadCommentBundle;
import com.jungdam.comment.dto.bundle.UpdateCommentBundle;
import com.jungdam.comment.dto.request.CreateCommentRequest;
import com.jungdam.comment.dto.request.UpdateCommentRequest;
import com.jungdam.comment.dto.response.CreateCommentResponse;
import com.jungdam.comment.dto.response.DeleteCommentResponse;
import com.jungdam.comment.dto.response.ReadCommentAllResponse;
import com.jungdam.comment.dto.response.UpdateCommentResponse;
import com.jungdam.comment.facade.CommentFacade;
import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api("Comment")
@RestController
@RequestMapping("/api/v1/albums/{albumId}/diaries/{diaryId}/comments")
public class CommentController {

    private final static int DEFAULT_PAGE_SIZE = 10;

    private final CommentFacade commentFacade;

    public CommentController(CommentFacade commentFacade) {
        this.commentFacade = commentFacade;
    }

    @ApiOperation("댓글 생성")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateCommentResponse>> create(@PathVariable Long albumId,
        @PathVariable Long diaryId, @RequestBody CreateCommentRequest request) {
        Long memberId = SecurityUtils.getCurrentUsername();

        CreateCommentBundle bundle = CreateCommentBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .diaryId(diaryId)
            .request(request)
            .build();

        CreateCommentResponse response = commentFacade.insert(bundle);

        return ResponseDto.of(ResponseMessage.COMMENT_CREATE_SUCCESS, response);
    }

    @ApiOperation("댓글 삭제")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseDto<DeleteCommentResponse>> delete(@PathVariable Long albumId,
        @PathVariable Long diaryId, @PathVariable Long commentId) {
        Long memberId = SecurityUtils.getCurrentUsername();

        DeleteCommentBundle bundle = DeleteCommentBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .diaryId(diaryId)
            .commentId(commentId)
            .albumId(albumId)
            .build();

        DeleteCommentResponse response = commentFacade.delete(bundle);

        return ResponseDto.of(ResponseMessage.COMMENT_DELETE_SUCCESS, response);
    }

    @ApiOperation("댓글 조회")
    @GetMapping
    public ResponseEntity<ResponseDto<ReadCommentAllResponse>> read(@PathVariable Long albumId,
        @PathVariable Long diaryId,
        @RequestParam(value = "cursorId", required = false) Long cursorId,
        @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        Long memberId = SecurityUtils.getCurrentUsername();

        if (Objects.isNull(pageSize)) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        ReadCommentBundle bundle = ReadCommentBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .diaryId(diaryId)
            .cusorId(cursorId)
            .pageSize(pageSize)
            .build();

        ReadCommentAllResponse response = commentFacade.find(bundle);

        return ResponseDto.of(ResponseMessage.COMMENT_READ_SUCCESS, response);
    }

    @ApiOperation("댓글 수정")
    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseDto<UpdateCommentResponse>> update(@PathVariable Long albumId,
        @PathVariable Long diaryId, @PathVariable Long commentId,
        @RequestBody UpdateCommentRequest request) {
        Long memberId = SecurityUtils.getCurrentUsername();

        UpdateCommentBundle bundle = UpdateCommentBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .diaryId(diaryId)
            .request(request)
            .commentId(commentId)
            .build();

        UpdateCommentResponse response = commentFacade.update(bundle);

        return ResponseDto.of(ResponseMessage.COMMENT_UPDATE_SUCCESS, response);
    }
}