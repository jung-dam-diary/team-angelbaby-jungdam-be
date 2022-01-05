package com.jungdam.auth.oauth2;

import com.jungdam.auth.oauth2.impl.GoogleOAuth2MemberInfo;
import com.jungdam.auth.oauth2.impl.KakaoOAuth2MemberInfo;
import com.jungdam.auth.oauth2.impl.NaverOAuth2MemberInfo;
import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.NotExistException;
import com.jungdam.member.domain.vo.ProviderType;
import java.util.Map;

public class OAuth2MemberInfoFactory {

    public static OAuth2MemberInfo of(ProviderType providerType,
        Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE:
                return new GoogleOAuth2MemberInfo(attributes);
            case NAVER:
                return new NaverOAuth2MemberInfo(attributes);
            case KAKAO:
                return new KakaoOAuth2MemberInfo(attributes);
            default:
                throw new NotExistException(ErrorMessage.NOT_EXIST_PROVIDER_TYPE);
        }
    }
}