package com.jungdam.participant.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.participant.dto.bundle.ReadAllParticipantBundle;
import com.jungdam.participant.dto.response.ReadAllParticipantResponse;
import com.jungdam.participant.facade.ParticipantFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/albums/{albumId}/participants")
public class ParticipantController {

    private final ParticipantFacade participantFacade;

    public ParticipantController(ParticipantFacade participantFacade) {
        this.participantFacade = participantFacade;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<ReadAllParticipantResponse>> read(
        @PathVariable Long albumId) {
        ReadAllParticipantBundle bundle = new ReadAllParticipantBundle(albumId);

        ReadAllParticipantResponse response = participantFacade.getAll(bundle);

        return ResponseDto.of(ResponseMessage.PARTICIPANT_READ_SUCCESS, response);
    }
}
