package com.jungdam.comment.converter;

import com.jungdam.comment.domain.Comment;
import com.jungdam.comment.dto.response.CreateCommentResponse;
import com.jungdam.comment.dto.response.DeleteCommentResponse;
import com.jungdam.diary.domain.Diary;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public CreateCommentResponse toCreateCommentResponse(Comment comment) {
        return new CreateCommentResponse(comment.getId(), comment.getContentValue());
    }

    public DeleteCommentResponse toDeleteCommentResponse(Diary diary) {
        return new DeleteCommentResponse(diary.getAlbumValue(), diary.getId());
    }
}