package com.jungdam.comment.presentation;

import com.jungdam.comment.dto.bundle.CreateCommentBundle;
import com.jungdam.comment.dto.request.CreateCommentRequest;
import com.jungdam.comment.dto.response.CreateCommentResponse;
import com.jungdam.comment.facade.CommentFacade;
import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api("Comment")
@Controller
@RequestMapping("/api/v1/albums/{albumId}/diaries/{diaryId}/comments")
public class CommentController {

    private final CommentFacade commentFacade;

    public CommentController(CommentFacade commentFacade) {
        this.commentFacade = commentFacade;
    }

    @ApiOperation("댓글 생성")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateCommentResponse>> create(@PathVariable Long albumId,
        @PathVariable Long diaryId, @RequestBody CreateCommentRequest request) {
        Long memberId = SecurityUtils.getCurrentUsername();

        CreateCommentBundle bundle = CreateCommentBundle.build()
            .memberId(memberId)
            .albumId(albumId)
            .diaryId(diaryId)
            .request(request)
            .build();

        CreateCommentResponse response = commentFacade.insert(bundle);

        return ResponseDto.of(ResponseMessage.COMMENT_CREATE_SUCCESS, response);
    }
}