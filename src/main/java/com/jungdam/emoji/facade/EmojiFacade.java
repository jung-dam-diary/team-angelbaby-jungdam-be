package com.jungdam.emoji.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.domain.Album;
import com.jungdam.diary.domain.Diary;
import com.jungdam.emoji.converter.EmojiConverter;
import com.jungdam.emoji.dto.bundle.CreateDeleteEmojiBundle;
import com.jungdam.emoji.dto.response.CreateDeleteEmojiResponse;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmojiFacade {

    private final MemberService memberService;
    private final AlbumService albumService;
    private final EmojiConverter emojiConverter;

    public EmojiFacade(MemberService memberService,
        AlbumService albumService, EmojiConverter emojiConverter) {
        this.memberService = memberService;
        this.albumService = albumService;
        this.emojiConverter = emojiConverter;
    }

    @Transactional
    public CreateDeleteEmojiResponse register(CreateDeleteEmojiBundle bundle) {
        Member member = memberService.findById(bundle.getMemberId());
        Album album = albumService.findById(bundle.getAlbumId());

        Diary diary = album.findDiary(bundle.getDiaryId());

        Participant participant = album.belong(member);
        boolean status = diary.register(participant, bundle.getContent());

        return emojiConverter.toCreateDeleteEmojiResponse(bundle.getContent(), status);
    }
}