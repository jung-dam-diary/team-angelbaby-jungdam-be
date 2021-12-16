package com.jungdam.member.presentation;

import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.dto.bundle.ReadMemberBundle;
import com.jungdam.member.dto.bundle.SearchMemberBundle;
import com.jungdam.member.dto.bundle.UpdateMemberBundle;
import com.jungdam.member.dto.request.UpdateMemberRequest;
import com.jungdam.member.dto.response.ReadMemberResponse;
import com.jungdam.member.dto.response.SearchMemberResponse;
import com.jungdam.member.dto.response.UpdateMemberResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("Member")
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ApiOperation("프로필 조회")
    @GetMapping
    public ResponseEntity<ResponseDto<ReadMemberResponse>> getProfile() {
        Long memberId = SecurityUtils.getCurrentUsername();

        ReadMemberBundle bundle = new ReadMemberBundle(memberId);
        ReadMemberResponse response = memberService.find(bundle);

        return ResponseDto.of(ResponseMessage.MEMBER_READ_SUCCESS, response);
    }

    @ApiOperation("회원 검색 조회(by email)")
    @GetMapping("/search")
    public ResponseEntity<ResponseDto<SearchMemberResponse>> getMemberByEmail(
        @RequestParam("email") String email) {

        SearchMemberBundle bundle = new SearchMemberBundle(email);

        SearchMemberResponse response = memberService.findByEmail(bundle);

        return ResponseDto.of(ResponseMessage.MEMBER_SEARCH_SUCCESS, response);
    }

    @ApiOperation("프로필 수정")
    @PutMapping
    public ResponseEntity<ResponseDto<UpdateMemberResponse>> update(@RequestBody UpdateMemberRequest request) {
        Long memberId = SecurityUtils.getCurrentUsername();

        UpdateMemberBundle bundle = new UpdateMemberBundle(memberId, request);
        UpdateMemberResponse response = memberService.update(bundle);

        return ResponseDto.of(ResponseMessage.MEMBER_UPDATE_SUCCESS, response);
    }
}