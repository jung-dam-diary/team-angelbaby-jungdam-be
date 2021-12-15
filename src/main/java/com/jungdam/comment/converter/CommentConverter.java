package com.jungdam.comment.converter;

import com.jungdam.comment.domain.Comment;
import com.jungdam.comment.dto.response.CreateCommentResponse;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public CreateCommentResponse toCreateCommentResponse(Comment comment) {
        return new CreateCommentResponse(comment.getId(), comment.getContentValue());
    }
}