package com.jungdam.comment.converter;

import com.jungdam.comment.domain.Comment;
import com.jungdam.comment.dto.response.CreateCommentResponse;
import com.jungdam.comment.dto.response.DeleteCommentResponse;
import com.jungdam.comment.dto.response.ReadCommentAllResponse;
import com.jungdam.comment.dto.response.ReadCommentResponse;
import com.jungdam.comment.dto.response.UpdateCommentResponse;
import com.jungdam.diary.domain.Diary;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public CreateCommentResponse toCreateCommentResponse(Comment comment) {
        return CreateCommentResponse.builder()
            .commentId(comment.getId())
            .commentContent(comment.getContentValue())
            .nickname(comment.getParticipantNicknameValue())
            .email(comment.getEmail())
            .avatar(comment.getParticipantAvatar())
            .build();
    }

    public DeleteCommentResponse toDeleteCommentResponse(Diary diary) {
        return new DeleteCommentResponse(diary.getAlbumValue(), diary.getId());
    }

    public ReadCommentAllResponse toReadAllCommentResponse(boolean hasNext, Long lastCommentId,
        List<Comment> comments) {
        return ReadCommentAllResponse.builder()
            .hasNext(hasNext)
            .lastCommentId(lastCommentId)
            .comments(toReadCommentResponses(comments))
            .build();
    }

    private List<ReadCommentResponse> toReadCommentResponses(List<Comment> comments) {
        return comments.stream()
            .map(this::toReadCommentResponse)
            .collect(Collectors.toList());
    }

    private ReadCommentResponse toReadCommentResponse(Comment comment) {
        return ReadCommentResponse.builder()
            .commentId(comment.getId())
            .commentContent(comment.getContentValue())
            .email(comment.getEmail())
            .nickname(comment.getParticipantNicknameValue())
            .avatar(comment.getParticipantAvatar())
            .build();
    }

    public UpdateCommentResponse toUpdateCommentResponse(Comment comment) {
        return new UpdateCommentResponse(comment.getDiaryIdValue(), comment.getContentValue());
    }
}