package com.jungdam.participant.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.domain.Album;
import com.jungdam.participant.application.ParticipantService;
import com.jungdam.participant.converter.ParticipantConverter;
import com.jungdam.participant.domain.Participant;
import com.jungdam.participant.dto.bundle.ReadAllParticipantBundle;
import com.jungdam.participant.dto.response.ReadAllParticipantResponse;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ParticipantFacade {

    private final AlbumService albumService;
    private final ParticipantService participantService;
    private final ParticipantConverter participantConverter;

    public ParticipantFacade(AlbumService albumService,
        ParticipantService participantService,
        ParticipantConverter participantConverter) {
        this.albumService = albumService;
        this.participantService = participantService;
        this.participantConverter = participantConverter;
    }

    @Transactional
    public ReadAllParticipantResponse getAll(ReadAllParticipantBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());

        List<Participant> participants = participantService.findAllByAlbum(album);

        return participantConverter.toReadAllParticipantResponse(participants);
    }
}
