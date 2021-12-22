package com.jungdam.participant.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import com.jungdam.participant.dto.bundle.CheckParticipantBundle;
import com.jungdam.participant.dto.bundle.ReadAllParticipantBundle;
import com.jungdam.participant.dto.bundle.ReadParticipantRoleBundle;
import com.jungdam.participant.dto.bundle.UpdateNicknameParticipantBundle;
import com.jungdam.participant.dto.request.UpdateNicknameParticipantRequest;
import com.jungdam.participant.dto.response.CheckParticipantResponse;
import com.jungdam.participant.dto.response.ReadAllParticipantResponse;
import com.jungdam.participant.dto.response.ReadParticipantRoleResponse;
import com.jungdam.participant.dto.response.UpdateNicknameParticipantResponse;
import com.jungdam.participant.facade.ParticipantFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @GetMapping
    public ResponseEntity<ResponseDto<ReadAllParticipantResponse>> read(
        @PathVariable Long albumId) {
        Long memberId = SecurityUtils.getCurrentUsername();

        ReadAllParticipantBundle bundle = new ReadAllParticipantBundle(albumId, memberId);

        ReadAllParticipantResponse response = participantFacade.findAll(bundle);

        return ResponseDto.of(ResponseMessage.PARTICIPANT_READ_SUCCESS, response);
    }

    @ApiOperation("참여인원 확인")
    @GetMapping("/me")
    public ResponseEntity<ResponseDto<CheckParticipantResponse>> check(@PathVariable Long albumId) {
        Long memberId = SecurityUtils.getCurrentUsername();

        CheckParticipantBundle bundle = new CheckParticipantBundle(memberId, albumId);

        CheckParticipantResponse response = participantFacade.check(bundle);

        return ResponseDto.of(ResponseMessage.PARTICIPANT_CHECK_SUCCESS, response);
    }

    @ApiOperation("참여자 닉네임 변경")
    @PutMapping
    public ResponseEntity<ResponseDto<UpdateNicknameParticipantResponse>> update(
        @PathVariable Long albumId,
        @RequestBody UpdateNicknameParticipantRequest request) {
        Long memberId = SecurityUtils.getCurrentUsername();

        UpdateNicknameParticipantBundle bundle = UpdateNicknameParticipantBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .request(request)
            .build();

        UpdateNicknameParticipantResponse response = participantFacade.updateNickname(bundle);

        return ResponseDto.of(ResponseMessage.PARTICIPANT_UPDATE_NICKNAME_SUCCESS, response);
    }

    @ApiOperation("참여자 역할 조회")
    @GetMapping("role")
    public ResponseEntity<ResponseDto<ReadParticipantRoleResponse>> getRole(@PathVariable Long albumId) {
        Long memberId = SecurityUtils.getCurrentUsername();

        ReadParticipantRoleBundle bundle = new ReadParticipantRoleBundle(memberId, albumId);
        ReadParticipantRoleResponse response = participantFacade.findRole(bundle);

        return ResponseDto.of(ResponseMessage.PARTICIPANT_ROLE_READ_SUCCESS, response);
    }
}