package com.jungdam.invitation.infrastructure;

import com.jungdam.album.domain.Album;
import com.jungdam.invitation.domain.Invitation;
import com.jungdam.invitation.domain.vo.Status;
import com.jungdam.member.domain.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    boolean existsByAlbumAndTargetMemberAndStatus(Album album, Member member, Status status);

    List<Invitation> findAllByTargetMemberAndStatus(Member member, Status status);
}
