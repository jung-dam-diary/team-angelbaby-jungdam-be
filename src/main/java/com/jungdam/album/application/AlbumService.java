package com.jungdam.album.application;

import com.jungdam.album.domain.Album;
import com.jungdam.album.dto.bundle.UpdateAlbumBundle;
import com.jungdam.album.infrastructure.AlbumRepository;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.NotExistException;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import com.jungdam.participant.domain.vo.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Transactional
    public Album save(Album album, Member member) {
        Participant participant = new Participant(member, Role.OWNER);
        album.addParticipant(participant);

        return albumRepository.save(album);
    }

    @Transactional(readOnly = true)
    public Album findById(Long id) {
        return albumRepository.findById(id)
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_ALBUM));
    }

    @Transactional
    public void delete(Album album) {
        albumRepository.delete(album);
    }

    @Transactional
    public Album update(Album album, UpdateAlbumBundle bundle) {
        album.update(
            bundle.getTitle(),
            bundle.getFamilyMotto(),
            bundle.getThumbnail()
        );
        return album;
    }
}