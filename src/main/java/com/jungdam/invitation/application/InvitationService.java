package com.jungdam.invitation.application;

import com.jungdam.album.domain.Album;
import com.jungdam.invitation.domain.Invitation;
import com.jungdam.invitation.domain.vo.Status;
import com.jungdam.invitation.infrastructure.InvitationRepository;
import com.jungdam.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;

    public InvitationService(
        InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @Transactional
    public Invitation save(Member targetMember, Member subjectMember) {
        Invitation invitation = new Invitation(targetMember, subjectMember);

        return invitationRepository.save(invitation);
    }

    @Transactional
    public boolean existsByAlbumAndTargetMemberAndStatus(Album album, Member member,
        Status status) {
        return invitationRepository.existsByAlbumAndTargetMemberAndStatus(album, member, status);
    }
}
