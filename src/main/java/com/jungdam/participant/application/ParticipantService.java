package com.jungdam.participant.application;

import com.jungdam.album.domain.Album;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.NotExistException;
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
    public boolean notExistsByAlbumAndMember(Album album, Member member) {
        return !participantRepository.existsByAlbumAndMember(album, member);
    }

    @Transactional(readOnly = true)
    public void checkNotExists(Album album, Member member) {
        if (!existsByAlbumAndMember(album, member)) {
            throw new NotExistException(ErrorMessage.NOT_EXIST_PARTICIPANT);
        }
    }

    private boolean existsByAlbumAndMember(Album album, Member member) {
        return participantRepository.existsByAlbumAndMember(album, member);
    }
}