package com.jungdam.invitation.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import com.jungdam.invitation.dto.bundle.CreateInvitationBundle;
import com.jungdam.invitation.dto.request.CreateInvitationRequest;
import com.jungdam.invitation.dto.response.CreateInvitationResponse;
import com.jungdam.invitation.facade.InvitationFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Invitation")
@RestController
@RequestMapping("/api/v1/albums/{albumId}/invitations")
public class InvitationController {

    private final InvitationFacade invitationFacade;

    public InvitationController(InvitationFacade invitationFacade) {
        this.invitationFacade = invitationFacade;
    }

    @ApiOperation("앨범으로 초대")
    @PostMapping("")
    public ResponseEntity<ResponseDto<CreateInvitationResponse>> invite(
        @PathVariable Long albumId, @RequestBody CreateInvitationRequest request) {
        Long subjectMemberId = SecurityUtils.getCurrentUsername();

        CreateInvitationBundle bundle = CreateInvitationBundle.builder()
            .subjectMemberId(subjectMemberId)
            .targetMemberId(request.getTargetMemberId())
            .albumId(albumId)
            .build();

        CreateInvitationResponse response = invitationFacade.insert(bundle);

        return ResponseDto.of(ResponseMessage.INVITATION_CREATE_SUCCESS, response);
    }
}
