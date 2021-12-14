package com.jungdam.participant.infrastructure;

import com.jungdam.album.domain.Album;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    boolean existsByAlbumAndMember(Album album, Member member);

    List<Participant> findAllByAlbum(Album album);
}