package com.jungdam.participant.application;

import com.jungdam.album.domain.Album;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.infrastructure.ParticipantRepository;
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
}