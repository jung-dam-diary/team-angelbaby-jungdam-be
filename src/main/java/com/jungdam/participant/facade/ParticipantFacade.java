package com.jungdam.participant.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.domain.Album;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.NotExistException;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.application.ParticipantService;
import com.jungdam.participant.converter.ParticipantConverter;
import com.jungdam.participant.domain.Participant;
import com.jungdam.participant.dto.bundle.ReadAllParticipantBundle;
import com.jungdam.participant.dto.response.ReadAllParticipantResponse;
import java.util.List;
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

    @Transactional
    public ReadAllParticipantResponse getAll(ReadAllParticipantBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        if (participantService.notExistsByAlbumAndMember(album, member)) {
            throw new NotExistException(ErrorMessage.NOT_EXIST_PARTICIPANT);
        }

        List<Participant> participants = participantService.findAllByAlbum(album);

        return participantConverter.toReadAllParticipantResponse(participants);
    }
}
