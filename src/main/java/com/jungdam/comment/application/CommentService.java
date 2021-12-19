package com.jungdam.comment.application;

import com.jungdam.comment.converter.CommentConverter;
import com.jungdam.comment.domain.Comment;
import com.jungdam.comment.domain.vo.Content;
import com.jungdam.comment.dto.response.ReadCommentAllResponse;
import com.jungdam.comment.infrastructure.CommentRepository;
import com.jungdam.diary.domain.Diary;
import com.jungdam.participant.domain.Participant;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final static int DEFAULT_PAGE = 0;
    private final static long NOT_EXISTS_COMMENT_NUMBER = -1;

    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;

    public CommentService(CommentRepository commentRepository,
        CommentConverter commentConverter) {
        this.commentRepository = commentRepository;
        this.commentConverter = commentConverter;
    }

    @Transactional
    public Comment save(Content content, Participant participant, Diary diary) {
        Comment comment = new Comment(content, participant);
        diary.addComment(comment);
        return commentRepository.save(comment);
    }

    // TODO
    @Transactional(readOnly = true)
    public ReadCommentAllResponse find(Participant participant, Diary diary,
        Long id, int size) {
        List<Comment> comments = findByDiaryAndId(diary,
            id, pageSetup(size));

        if (comments.isEmpty()) {
            return commentConverter.toReadAllCommentResponse(false, NOT_EXISTS_COMMENT_NUMBER,
                comments);
        }
        final Comment lastDiaryOfList = comments.get(comments.size() - 1);

        Long finalNumber = lastDiaryOfList.getId();

        return makeReadCommentAllResponse(participant, diary, comments, finalNumber);
    }

    private ReadCommentAllResponse makeReadCommentAllResponse(Participant participant, Diary diary,
        List<Comment> comments, Long finalNumber) {
        if (hasNext(diary, participant, finalNumber)) {
            return commentConverter.toReadAllCommentResponse(true, finalNumber, comments);
        }
        return commentConverter.toReadAllCommentResponse(false, NOT_EXISTS_COMMENT_NUMBER,
            comments);
    }

    private Boolean hasNext(Diary diary, Participant participant, Long id) {
        return commentRepository.existsByDiaryAndParticipantAndIdLessThan(diary, participant, id);
    }

    private List<Comment> findByDiaryAndId(Diary diary, Long cursorId,
        Pageable page) {
        if (Objects.isNull(cursorId)) {
            return findAllByDiaryOrderByIdDesc(diary, page);
        }
        return findAllByDiaryAndIdLessThanOrderByIdDesc(diary, cursorId, page);
    }

    private List<Comment> findAllByDiaryOrderByIdDesc(Diary diary, Pageable page) {
        return commentRepository.findAllByDiaryOrderByIdDesc(diary, page);
    }

    private List<Comment> findAllByDiaryAndIdLessThanOrderByIdDesc(Diary diary, Long cursorId,
        Pageable page) {
        return commentRepository.findAllByDiaryAndIdLessThanOrderByIdDesc(diary,
            cursorId, page);
    }

    private Pageable pageSetup(int size) {
        return PageRequest.of(DEFAULT_PAGE, size);
    }
}