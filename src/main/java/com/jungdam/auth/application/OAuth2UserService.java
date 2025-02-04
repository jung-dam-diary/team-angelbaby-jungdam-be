package com.jungdam.auth.application;


import com.jungdam.auth.domain.AuthPrincipal;
import com.jungdam.auth.oauth2.OAuth2MemberInfo;
import com.jungdam.auth.oauth2.OAuth2MemberInfoFactory;
import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.auth.FailAuthenticationException;
import com.jungdam.error.exception.auth.InternalAuthenticationException;
import com.jungdam.error.exception.common.DuplicationException;
import com.jungdam.member.converter.MemberConverter;
import com.jungdam.member.domain.Member;
import com.jungdam.member.domain.vo.Email;
import com.jungdam.member.domain.vo.ProviderType;
import com.jungdam.member.infrastructure.MemberRepository;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final static Logger log = LoggerFactory.getLogger(OAuth2UserService.class);

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    public OAuth2UserService(MemberRepository memberRepository,
        MemberConverter memberConverter) {
        this.memberRepository = memberRepository;
        this.memberConverter = memberConverter;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest)
        throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return this.process(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException exception) {
            log.error(exception.getMessage(), this);
            throw new FailAuthenticationException(ErrorMessage.FAIL_TO_LOGIN_OAUTH2);
        } catch (Exception exception) {
            log.error(exception.getMessage(), this);
            throw new InternalAuthenticationException(ErrorMessage.FAIL_TO_LOGIN_OAUTH2);
        }
    }

    private OAuth2User process(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        ProviderType providerType = bringProviderType(oAuth2UserRequest.getClientRegistration());

        OAuth2MemberInfo oAuth2MemberInfo = OAuth2MemberInfoFactory.of(
            providerType,
            oAuth2User.getAttributes()
        );

        Member member = memberRepository.findByOauthPermission(
            oAuth2MemberInfo.getOauthPermission()
        );

        if (Objects.isNull(member)) {
            Email email = new Email(oAuth2MemberInfo.getEmail());
            isExistsEmail(email);
            member = create(oAuth2MemberInfo, providerType);
        }
        return AuthPrincipal.create(member, oAuth2User.getAttributes());
    }

    private void isExistsEmail(Email email) {
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicationException(ErrorMessage.ALREADY_EXIST_MEMBER_EMAIL).error(log);
        }
    }

    private ProviderType bringProviderType(ClientRegistration clientRegistration) {
        String provider = clientRegistration.getRegistrationId()
            .toUpperCase();
        return ProviderType.of(provider);
    }

    private Member create(OAuth2MemberInfo userInfo, ProviderType providerType) {
        Member member = memberConverter.toMember(userInfo, providerType);
        return memberRepository.save(member);
    }
}