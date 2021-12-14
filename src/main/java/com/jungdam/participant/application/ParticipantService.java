package com.jungdam.participant.application;

import com.jungdam.album.domain.Album;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import com.jungdam.participant.infrastructure.ParticipantRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(
        ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Transactional(readOnly = true)
    public boolean existsByAlbumAndMember(Album album, Member member) {
        return participantRepository.existsByAlbumAndMember(album, member);
    }

    @Transactional(readOnly = true)
    public List<Participant> findAllByAlbum(Album album) {
        return participantRepository.findAllByAlbum(album);
    }
}