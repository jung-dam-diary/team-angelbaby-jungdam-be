package com.jungdam.comment.converter;

import com.jungdam.comment.domain.Comment;
import com.jungdam.comment.dto.response.CreateCommentResponse;
import com.jungdam.comment.dto.response.DeleteCommentResponse;
import com.jungdam.comment.dto.response.ReadCommentAllResponse;
import com.jungdam.comment.dto.response.ReadCommentResponse;
import com.jungdam.diary.domain.Diary;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public CreateCommentResponse toCreateCommentResponse(Comment comment) {
        return new CreateCommentResponse(comment.getId(), comment.getContentValue());
    }

    public DeleteCommentResponse toDeleteCommentResponse(Diary diary) {
        return new DeleteCommentResponse(diary.getAlbumValue(), diary.getId());
    }

    public ReadCommentAllResponse toReadCommentsResponse(boolean hasNext, List<Comment> comments) {
        List<ReadCommentResponse> collect = comments.stream()
            .map(c -> ReadCommentResponse.builder()
                .commentId(c.getId())
                .commentContent(c.getContentValue())
                .nickname(c.getMemberNicknameValue())
                .build()).collect(Collectors.toList());

        return new ReadCommentAllResponse(hasNext, collect);
    }
}