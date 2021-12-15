package com.jungdam.participant.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import com.jungdam.participant.dto.bundle.ReadAllParticipantBundle;
import com.jungdam.participant.dto.response.ReadAllParticipantResponse;
import com.jungdam.participant.facade.ParticipantFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Participant")
@RestController
@RequestMapping("/api/v1/albums/{albumId}/participants")
public class ParticipantController {

    private final ParticipantFacade participantFacade;

    public ParticipantController(ParticipantFacade participantFacade) {
        this.participantFacade = participantFacade;
    }

    @ApiOperation("멤버 리스트 조회")
    @GetMapping()
    public ResponseEntity<ResponseDto<ReadAllParticipantResponse>> read(
        @PathVariable Long albumId) {
        Long memberId = SecurityUtils.getCurrentUsername();

        ReadAllParticipantBundle bundle = new ReadAllParticipantBundle(albumId, memberId);

        ReadAllParticipantResponse response = participantFacade.findAll(bundle);

        return ResponseDto.of(ResponseMessage.PARTICIPANT_READ_SUCCESS, response);
    }
}
