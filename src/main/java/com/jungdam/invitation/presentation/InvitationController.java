package com.jungdam.invitation.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import com.jungdam.invitation.dto.bundle.CreateInvitationBundle;
import com.jungdam.invitation.dto.bundle.ReadAllInvitationBundle;
import com.jungdam.invitation.dto.bundle.SearchInvitationBundle;
import com.jungdam.invitation.dto.bundle.UpdateInvitationBundle;
import com.jungdam.invitation.dto.request.CreateInvitationRequest;
import com.jungdam.invitation.dto.request.UpdateInvitationRequest;
import com.jungdam.invitation.dto.response.CreateInvitationResponse;
import com.jungdam.invitation.dto.response.ReadAllInvitationResponse;
import com.jungdam.invitation.dto.response.UpdateInvitationResponse;
import com.jungdam.invitation.facade.InvitationFacade;
import com.jungdam.member.dto.response.SearchMemberResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("Invitation")
@RestController
@RequestMapping("/api/v1")
public class InvitationController {

    private final InvitationFacade invitationFacade;

    public InvitationController(InvitationFacade invitationFacade) {
        this.invitationFacade = invitationFacade;
    }

    @ApiOperation("회원 검색 조회(by email)")
    @GetMapping("/albums/{albumId}/search")
    public ResponseEntity<ResponseDto<SearchMemberResponse>> searchMember(
        @PathVariable Long albumId, @RequestParam("email") String email) {
        Long memberId = SecurityUtils.getCurrentUsername();

        SearchInvitationBundle bundle = SearchInvitationBundle.builder()
            .memberId(memberId)
            .albumId(albumId)
            .email(email)
            .build();

        SearchMemberResponse response = invitationFacade.search(bundle);

        return ResponseDto.of(ResponseMessage.MEMBER_SEARCH_SUCCESS, response);
    }

    @ApiOperation("앨범으로 초대")
    @PostMapping("/albums/{albumId}/invitations")
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

    @ApiOperation("초대 목록 조회")
    @GetMapping("/invitations")
    public ResponseEntity<ResponseDto<List<ReadAllInvitationResponse>>> getAllWithPendingStatus() {
        Long memberId = SecurityUtils.getCurrentUsername();

        ReadAllInvitationBundle bundle = new ReadAllInvitationBundle(memberId);
        List<ReadAllInvitationResponse> responseList = invitationFacade.findAllWithPendingStatus(
            bundle);

        return ResponseDto.of(ResponseMessage.INVITATION_READ_ALL_SUCCESS, responseList);
    }

    @ApiOperation("초대 수락/거절")
    @PutMapping("/invitations/{invitationId}")
    public ResponseEntity<ResponseDto<UpdateInvitationResponse>> update(
        @PathVariable Long invitationId, @RequestBody UpdateInvitationRequest request) {
        Long memberId = SecurityUtils.getCurrentUsername();

        UpdateInvitationBundle bundle = UpdateInvitationBundle.builder()
            .memberId(memberId)
            .invitationId(invitationId)
            .request(request)
            .build();
        UpdateInvitationResponse response = invitationFacade.update(bundle);

        return ResponseDto.of(ResponseMessage.INVITATION_UPDATE_SUCCESS, response);
    }
}
