package com.jungdam.member.converter;

import com.jungdam.auth.oauth2.OAuth2MemberInfo;
import com.jungdam.member.domain.Member;
import com.jungdam.member.domain.vo.Avatar;
import com.jungdam.member.domain.vo.Email;
import com.jungdam.member.domain.vo.Nickname;
import com.jungdam.member.domain.vo.ProviderType;
import com.jungdam.member.dto.response.ReadMemberResponse;
import com.jungdam.member.dto.response.SearchMemberResponse;
import com.jungdam.member.dto.response.UpdateMemberResponse;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {

    public Member toMember(OAuth2MemberInfo userInfo, ProviderType providerType) {
        return Member.builder()
            .oauthPermission(userInfo.getOauthPermission())
            .nickname(new Nickname(userInfo.getNickname()))
            .email(new Email(userInfo.getEmail()))
            .avatar(new Avatar(userInfo.getAvatar()))
            .providerType(providerType)
            .build();
    }

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

    public UpdateMemberResponse toUpdateMemberResponse(Member member) {
        return new UpdateMemberResponse(member.getNicknameValue(), member.getAvatarValue());
    }
}