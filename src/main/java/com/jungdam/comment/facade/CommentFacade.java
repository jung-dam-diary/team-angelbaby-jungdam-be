package com.jungdam.comment.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.domain.Album;
import com.jungdam.comment.application.CommentService;
import com.jungdam.comment.converter.CommentConverter;
import com.jungdam.comment.domain.Comment;
import com.jungdam.comment.dto.bundle.CreateCommentBundle;
import com.jungdam.comment.dto.bundle.DeleteCommentBundle;
import com.jungdam.comment.dto.bundle.ReadCommentBundle;
import com.jungdam.comment.dto.bundle.UpdateCommentBundle;
import com.jungdam.comment.dto.response.CreateCommentResponse;
import com.jungdam.comment.dto.response.DeleteCommentResponse;
import com.jungdam.comment.dto.response.ReadCommentAllResponse;
import com.jungdam.comment.dto.response.UpdateCommentResponse;
import com.jungdam.common.utils.PageUtil;
import com.jungdam.diary.domain.Diary;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CommentFacade {

    private final MemberService memberService;
    private final AlbumService albumService;
    private final CommentConverter commentConverter;
    private final CommentService commentService;

    public CommentFacade(MemberService memberService,
        AlbumService albumService,
        CommentConverter commentConverter,
        CommentService commentService) {
        this.memberService = memberService;
        this.albumService = albumService;
        this.commentConverter = commentConverter;
        this.commentService = commentService;
    }

    @Transactional
    public CreateCommentResponse insert(CreateCommentBundle bundle) {
        Member member = memberService.findById(bundle.getMemberId());
        Album album = albumService.findById(bundle.getAlbumId());

        Diary diary = album.findDiary(bundle.getDiaryId());

        Participant participant = album.belong(member);

        Comment comment = commentService.save(bundle.getContent(), participant, diary);

        return commentConverter.toCreateCommentResponse(comment);
    }

    @Transactional
    public DeleteCommentResponse delete(DeleteCommentBundle bundle) {
        Member member = memberService.findById(bundle.getMemberId());
        Album album = albumService.findById(bundle.getAlbumId());

        Diary diary = album.findDiary(bundle.getDiaryId());

        Participant participant = album.belong(member);

        diary.deleteContent(bundle.getCommentId(), participant);

        return commentConverter.toDeleteCommentResponse(diary);
    }

    @Transactional(readOnly = true)
    public ReadCommentAllResponse find(ReadCommentBundle bundle) {
        Member member = memberService.findById(bundle.getMemberId());
        Album album = albumService.findById(bundle.getAlbumId());

        Diary diary = album.findDiary(bundle.getDiaryId());

        Participant participant = album.belong(member);

        Pageable page = PageUtil.of(bundle.getPageSize());

        return commentService.find(participant, diary, bundle.getCursorId(), page);
    }

    @Transactional
    public UpdateCommentResponse update(UpdateCommentBundle bundle) {
        Member member = memberService.findById(bundle.getMemberId());
        Album album = albumService.findById(bundle.getAlbumId());

        Participant participant = album.belong(member);

        Diary diary = album.findDiary(bundle.getDiaryId());

        Comment comment = diary.findComment(bundle.getCommentId(), participant);
        comment.update(bundle.getContent());

        return commentConverter.toUpdateCommentResponse(comment);
    }
}