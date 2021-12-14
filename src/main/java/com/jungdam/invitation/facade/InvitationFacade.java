package com.jungdam.invitation.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.domain.Album;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.DuplicationException;
import com.jungdam.invitation.application.InvitationService;
import com.jungdam.invitation.domain.Invitation;
import com.jungdam.invitation.domain.vo.Status;
import com.jungdam.invitation.dto.bundle.CreateInvitationBundle;
import com.jungdam.invitation.dto.response.CreateInvitationResponse;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.application.ParticipantService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InvitationFacade {

    private final InvitationService invitationService;
    private final AlbumService albumService;
    private final MemberService memberService;
    private final ParticipantService participantService;

    public InvitationFacade(InvitationService invitationService,
        AlbumService albumService, MemberService memberService,
        ParticipantService participantService) {
        this.invitationService = invitationService;
        this.albumService = albumService;
        this.memberService = memberService;
        this.participantService = participantService;
    }

    @Transactional
    public CreateInvitationResponse insert(CreateInvitationBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member targetMember = memberService.findById(bundle.getTargetMemberId());
        Member subjectMember = memberService.findById(bundle.getSubjectMemberId());

        if (invitationService.existsByAlbumAndTargetMemberAndStatus(album, targetMember,
            Status.PENDING)) {
            throw new DuplicationException(ErrorMessage.DUPLICATION_INVITATION_IN_ALBUM);
        }

        if (participantService.existsByAlbumAndMember(album, targetMember)) {
            throw new DuplicationException(ErrorMessage.DUPLICATION_PARTICIPANT_IN_ALBUM);
        }

        Invitation invitation = invitationService.save(
            targetMember,
            subjectMember
        );

        album.addInvitation(invitation);

        return new CreateInvitationResponse(invitation.getId());
    }
}
