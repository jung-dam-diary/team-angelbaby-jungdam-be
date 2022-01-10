package com.jungdam.participant.application;

import com.jungdam.album.domain.Album;
import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.NoPermissionException;
import com.jungdam.error.exception.common.NotExistException;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import com.jungdam.participant.domain.vo.Role;
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

    @Transactional
    public void saveAlbumMemberRole(Member member, Album album) {
        Participant participant = new Participant(member, Role.MEMBER);
        participant.register(album);
        participantRepository.save(participant);
    }
    
    @Transactional(readOnly = true)
    public List<Participant> findAllByAlbum(Album album) {
        return participantRepository.findAllByAlbum(album);
    }

    @Transactional(readOnly = true)
    public List<Participant> findAllByMember(Member member) {
        return participantRepository.findAllByMember(member);
    }

    @Transactional(readOnly = true)
    public void checkExists(Album album, Member member) {
        if (!existsByAlbumAndMember(album, member)) {
            throw new NotExistException(ErrorMessage.DUPLICATION_PARTICIPANT_IN_ALBUM);
        }
    }

    public boolean existsByAlbumAndMember(Album album, Member member) {
        return participantRepository.existsByAlbumAndMember(album, member);
    }

    // TODO: 어노테이션 리펙토링
    @Transactional(readOnly = true)
    public void checkMemberIsOwnerRole(Album album, Member member) {
        if (!existsByAlbumAndOwnerMember(album, member)) {
            throw new NoPermissionException(ErrorMessage.NON_PERMISSION_ALBUM);
        }
    }

    private boolean existsByAlbumAndOwnerMember(Album album, Member member) {
        return participantRepository.existsByAlbumAndMemberAndRole(album, member, Role.OWNER);
    }

    public Participant findById(Long participantId) {
        return participantRepository.findById(participantId)
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_PARTICIPANT));
    }

    public Participant findByMemberAndAlbum(Member member, Album album) {
        return participantRepository.findByMemberAndAlbum(member, album)
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_PARTICIPANT));
    }
}