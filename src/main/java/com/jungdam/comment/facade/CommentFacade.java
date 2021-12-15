package com.jungdam.comment.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.domain.Album;
import com.jungdam.comment.application.CommentService;
import com.jungdam.comment.converter.CommentConverter;
import com.jungdam.comment.domain.Comment;
import com.jungdam.comment.dto.bundle.CreateCommentBundle;
import com.jungdam.comment.dto.response.CreateCommentResponse;
import com.jungdam.diary.application.DiaryService;
import com.jungdam.diary.domain.Diary;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.application.ParticipantService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CommentFacade {

    private final MemberService memberService;
    private final AlbumService albumService;
    private final DiaryService diaryService;
    private final ParticipantService participantService;
    private final CommentConverter commentConverter;
    private final CommentService commentService;

    public CommentFacade(MemberService memberService,
        AlbumService albumService, DiaryService diaryService,
        ParticipantService participantService,
        CommentConverter commentConverter,
        CommentService commentService) {
        this.memberService = memberService;
        this.albumService = albumService;
        this.diaryService = diaryService;
        this.participantService = participantService;
        this.commentConverter = commentConverter;
        this.commentService = commentService;
    }

    @Transactional
    public CreateCommentResponse create(CreateCommentBundle bundle) {
        Member member = memberService.findById(bundle.getMemberId());
        Album album = albumService.findById(bundle.getAlbumId());
        Diary diary = diaryService.findById(bundle.getDiaryId());

        participantService.checkNotExists(album, member);

        Comment comment = commentService.save(bundle.getContent(), member, diary);

        return commentConverter.toCreateCommentResponse(comment);
    }
}