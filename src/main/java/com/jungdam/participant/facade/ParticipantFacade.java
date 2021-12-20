package com.jungdam.participant.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.domain.Album;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.application.ParticipantService;
import com.jungdam.participant.converter.ParticipantConverter;
import com.jungdam.participant.domain.Participant;
import com.jungdam.participant.dto.bundle.CheckParticipantBundle;
import com.jungdam.participant.dto.bundle.ReadAllParticipantBundle;
import com.jungdam.participant.dto.bundle.UpdateNicknameParticipantBundle;
import com.jungdam.participant.dto.response.CheckParticipantResponse;
import com.jungdam.participant.dto.response.ReadAllParticipantResponse;
import com.jungdam.participant.dto.response.UpdateNicknameParticipantResponse;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ParticipantFacade {

    private final ParticipantConverter participantConverter;
    private final ParticipantService participantService;
    private final AlbumService albumService;
    private final MemberService memberService;

    public ParticipantFacade(AlbumService albumService,
        MemberService memberService,
        ParticipantService participantService,
        ParticipantConverter participantConverter) {
        this.albumService = albumService;
        this.memberService = memberService;
        this.participantService = participantService;
        this.participantConverter = participantConverter;
    }

    @Transactional(readOnly = true)
    public ReadAllParticipantResponse findAll(ReadAllParticipantBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        album.belong(member);

        List<Participant> participants = album.getParticipants();

        return participantConverter.toReadAllParticipantResponse(participants);
    }

    @Transactional(readOnly = true)
    public CheckParticipantResponse check(CheckParticipantBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        final Participant belong = album.belong(member);
        boolean check = !Objects.isNull(belong);

        return participantConverter.toCheckParticipantResponse(album, check);
    }

    @Transactional
    public UpdateNicknameParticipantResponse updateNickname(
        UpdateNicknameParticipantBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        Participant participant = album.belong(member);
        participant.updateNickname(bundle.getNickname());

        return participantConverter.toUpdateNicknameParticipantResponse(participant);
    }
}