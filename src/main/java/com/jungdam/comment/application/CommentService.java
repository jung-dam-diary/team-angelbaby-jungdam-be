package com.jungdam.comment.application;

import com.jungdam.comment.domain.Comment;
import com.jungdam.comment.domain.vo.Content;
import com.jungdam.comment.infrastructure.CommentRepository;
import com.jungdam.diary.domain.Diary;
import com.jungdam.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment save(Content content, Member member, Diary diary) {
        Comment comment = new Comment(content, member);
        diary.addComment(comment);
        return commentRepository.save(comment);
    }
}