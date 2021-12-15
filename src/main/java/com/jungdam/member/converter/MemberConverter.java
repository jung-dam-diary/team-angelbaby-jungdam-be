package com.jungdam.member.converter;

import com.jungdam.member.domain.Member;
import com.jungdam.member.dto.response.ReadMemberResponse;
import com.jungdam.member.dto.response.SearchMemberResponse;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {

    public ReadMemberResponse toReadMemberResponse(Member member) {
        return ReadMemberResponse.builder()
            .email(member.getEmailValue())
            .nickname(member.getNicknameValue())
            .avatar(member.getAvatarValue())
            .role(member.getRoleValue())
            .build();
    }

    public SearchMemberResponse toSearchMemberResponse(Member member) {
        return SearchMemberResponse.builder()
            .email(member.getEmailValue())
            .nickname(member.getNicknameValue())
            .avatar(member.getAvatarValue())
            .build();
    }
}