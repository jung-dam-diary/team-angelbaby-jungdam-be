package com.jungdam.invitation.application;

import com.jungdam.album.domain.Album;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.DuplicationException;
import com.jungdam.invitation.domain.Invitation;
import com.jungdam.invitation.domain.vo.Status;
import com.jungdam.invitation.infrastructure.InvitationRepository;
import com.jungdam.member.domain.Member;
import java.util.List;
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

    @Transactional(readOnly = true)
    public List<Invitation> findAllByTargetMemberAndPendingStatus(Member member) {
        return invitationRepository.findAllByTargetMemberAndStatus(member, Status.PENDING);
    }

    @Transactional(readOnly = true)
    public void checkExistsInPendingStatus(Album album, Member member) {
        if (invitationRepository.existsByAlbumAndTargetMemberAndStatus(album, member,
            Status.PENDING)) {
            throw new DuplicationException(ErrorMessage.DUPLICATION_INVITATION_IN_ALBUM);
        }
    }
}
