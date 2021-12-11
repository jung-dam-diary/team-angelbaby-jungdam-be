package com.jungdam.member.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.dto.bundle.ReadMemberBundle;
import com.jungdam.member.dto.response.ReadMemberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<ReadMemberResponse>> findMember() {

        Long memberId = SecurityUtils.getCurrentUsername();

        ReadMemberBundle bundle = new ReadMemberBundle(memberId);

        ReadMemberResponse response = memberService.find(bundle);

        return ResponseEntity.status(ResponseMessage.MEMBER_READ_SUCCESS.getStatus())
            .body(
                ResponseDto.of(
                    ResponseMessage.MEMBER_READ_SUCCESS,
                    response
                )
            );
    }
}