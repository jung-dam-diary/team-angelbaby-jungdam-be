package com.jungdam.member.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.dto.bundle.ReadMemberBundle;
import com.jungdam.member.dto.response.ReadMemberResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Member")
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ApiOperation("사용자 조회")
    @GetMapping()
    public ResponseEntity<ResponseDto<ReadMemberResponse>> findMember() {

        Long memberId = SecurityUtils.getCurrentUsername();

        ReadMemberBundle bundle = new ReadMemberBundle(memberId);

        ReadMemberResponse response = memberService.find(bundle);

        return ResponseDto.of(ResponseMessage.MEMBER_READ_SUCCESS, response);
    }
}