package com.jungdam.auth.application;


import com.jungdam.auth.domain.AuthPrincipal;
import com.jungdam.auth.oauth2.OAuth2MemberInfo;
import com.jungdam.auth.oauth2.OAuth2MemberInfoFactory;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.OAuthProviderMissMatchException;
import com.jungdam.member.domain.Member;
import com.jungdam.member.domain.vo.ProviderType;
import com.jungdam.member.infrastructure.MemberRepository;
import java.util.Objects;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    public CustomOAuth2UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest)
        throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return this.process(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException exception) {
            throw exception;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new InternalAuthenticationServiceException(exception.getMessage(),
                exception.getCause());
        }
    }

    // 추가로 이메일 검증 필요
    private OAuth2User process(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        ProviderType providerType = ProviderType.valueOf(
            oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase()
        );

        OAuth2MemberInfo oAuth2MemberInfo = OAuth2MemberInfoFactory.of(
            providerType,
            oAuth2User.getAttributes()
        );

        Member member = memberRepository.findByOauthPermission(
            oAuth2MemberInfo.getOauthPermission());

        if (!Objects.isNull(member)) {
            if (providerType != member.getProviderType()) {
                throw new OAuthProviderMissMatchException(ErrorMessage.NOT_EXIST_PROVIDER_TYPE);
            }
            update(member, oAuth2MemberInfo);
        } else {
            member = createUser(oAuth2MemberInfo, providerType);
        }

        return AuthPrincipal.create(member, oAuth2User.getAttributes());
    }

    private Member createUser(OAuth2MemberInfo userInfo, ProviderType providerType) {
        Member member = new Member(
            userInfo.getOauthPermission(),
            userInfo.getNickname(),
            userInfo.getEmail(),
            userInfo.getAvatar(),
            providerType
        );

        return memberRepository.saveAndFlush(member);
    }

    private Member update(Member member, OAuth2MemberInfo oAuth2MemberInfo) {
        if (!Objects.isNull(oAuth2MemberInfo.getNickname()) && !member.getNickname()
            .equals(oAuth2MemberInfo.getNickname())) {
            member.updateNickname(oAuth2MemberInfo.getNickname());
        }

        if (!Objects.isNull(oAuth2MemberInfo.getAvatar()) && !member.getAvatar()
            .equals(oAuth2MemberInfo.getAvatar())) {
            member.updateAvatar(oAuth2MemberInfo.getAvatar());
        }

        return member;
    }
}