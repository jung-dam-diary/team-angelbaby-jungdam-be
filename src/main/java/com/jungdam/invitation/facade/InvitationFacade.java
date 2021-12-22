package com.jungdam.invitation.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.domain.Album;
import com.jungdam.invitation.application.InvitationService;
import com.jungdam.invitation.converter.InvitationConverter;
import com.jungdam.invitation.domain.Invitation;
import com.jungdam.invitation.dto.bundle.CreateInvitationBundle;
import com.jungdam.invitation.dto.bundle.ReadAllInvitationBundle;
import com.jungdam.invitation.dto.bundle.SearchInvitationBundle;
import com.jungdam.invitation.dto.bundle.UpdateInvitationBundle;
import com.jungdam.invitation.dto.response.CreateInvitationResponse;
import com.jungdam.invitation.dto.response.ReadAllInvitationResponse;
import com.jungdam.invitation.dto.response.UpdateInvitationResponse;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.converter.MemberConverter;
import com.jungdam.member.domain.Member;
import com.jungdam.member.dto.response.SearchMemberResponse;
import com.jungdam.participant.application.ParticipantService;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InvitationFacade {

    private final InvitationService invitationService;
    private final AlbumService albumService;
    private final MemberService memberService;
    private final ParticipantService participantService;
    private final InvitationConverter invitationConverter;
    private final MemberConverter memberConverter;

    public InvitationFacade(InvitationService invitationService,
        AlbumService albumService, MemberService memberService,
        ParticipantService participantService,
        InvitationConverter invitationConverter,
        MemberConverter memberConverter) {
        this.invitationService = invitationService;
        this.albumService = albumService;
        this.memberService = memberService;
        this.participantService = participantService;
        this.invitationConverter = invitationConverter;
        this.memberConverter = memberConverter;
    }

    @Transactional(readOnly = true)
    public SearchMemberResponse search(SearchInvitationBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());
        Member targetMember = memberService.findByEmailForSearch(bundle.getEmail());

        album.belong(member);

        album.existsParticipant(targetMember);
        album.existsInvitationParticipant(targetMember);

        return memberConverter.toSearchMemberResponse(targetMember);
    }

    @Transactional
    public CreateInvitationResponse insert(CreateInvitationBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member targetMember = memberService.findById(bundle.getTargetMemberId());
        Member subjectMember = memberService.findById(bundle.getSubjectMemberId());

        invitationService.checkExistsInPendingStatus(album, targetMember);

        album.existsParticipant(targetMember);

        Invitation invitation = invitationService.save(
            targetMember,
            subjectMember
        );

        album.addInvitation(invitation);

        return invitationConverter.toCreateInvitationResponse(invitation);
    }

    @Transactional(readOnly = true)
    public List<ReadAllInvitationResponse> findAllWithPendingStatus(
        ReadAllInvitationBundle bundle) {
        Member member = memberService.findById(bundle.getMemberId());

        List<Invitation> invitationList = invitationService.findAllByTargetMemberAndPendingStatus(
            member);

        return invitationConverter.toReadAllInvitationResponse(invitationList);
    }

    @Transactional
    public UpdateInvitationResponse update(UpdateInvitationBundle bundle) {
        Member member = memberService.findById(bundle.getMemberId());
        Invitation invitation = invitationService.findByIdAndTargetMemberAndPendingStatus(
            bundle.getInvitationId(), member);

        invitation.updateStatus(bundle.getStatus());
        if (invitation.isAccept()) {
            participantService.saveAlbumMemberRole(member, invitation.getAlbum());
        }

        return invitationConverter.toUpdateInvitationResponse(invitation);
    }
}

