package com.jungdam.comment.converter;

import com.jungdam.comment.domain.Comment;
import com.jungdam.comment.domain.vo.Content;
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
            .avatar(comment.getAvatar())
            .build();
    }

    public DeleteCommentResponse toDeleteCommentResponse(Diary diary) {
        return new DeleteCommentResponse(diary.getAlbumValue(), diary.getId());
    }

    public ReadCommentAllResponse toReadAllCommentResponse(boolean hasNext, Long lastCommentId,
        List<Comment> comments) {
        List<ReadCommentResponse> all = comments.stream()
            .map(c ->
                ReadCommentResponse.builder()
                    .commentId(c.getId())
                    .commentContent(c.getContentValue())
                    .email(c.getEmail())
                    .nickname(c.getParticipantNicknameValue())
                    .avatar(c.getAvatar())
                    .build())
            .collect(Collectors.toList());

        return ReadCommentAllResponse.builder()
            .hasNext(hasNext)
            .lastCommentId(lastCommentId)
            .comments(all)
            .build();
    }

    public UpdateCommentResponse toUpdateCommentResponse(Long id, Content content) {
        return new UpdateCommentResponse(id, content.getContent());
    }
}