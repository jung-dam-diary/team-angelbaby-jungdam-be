package com.jungdam.auth.oauth2.impl;

import com.jungdam.auth.oauth2.OAuth2MemberInfo;
import java.util.Map;
import java.util.Objects;

public class KakaoOAuth2MemberInfo extends OAuth2MemberInfo {

    private final static String OAUTH_ATTRIBUTES = "properties";
    private final static String OAUTH_PERMISSION_ATTRIBUTE = "id";
    private final static String NICKNAME_ATTRIBUTE = "nickname";
    private final static String EMAIL_ATTRIBUTE = "email";
    private final static String AVATAR_ATTRIBUTE = "thumbnail_image";
    private final static String KAKAO_OAUTH_ATTRIBUTES = "kakao_account";

    public KakaoOAuth2MemberInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getOauthPermission() {
        return attributes.get(OAUTH_PERMISSION_ATTRIBUTE).toString();
    }

    @Override
    public String getNickname() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get(
            OAUTH_ATTRIBUTES);

        if (Objects.isNull(properties)) {
            return null;
        }

        return (String) properties.get(NICKNAME_ATTRIBUTE);
    }

    @Override
    public String getEmail() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get(
            KAKAO_OAUTH_ATTRIBUTES);

        if (Objects.isNull(properties)) {
            return null;
        }

        return (String) properties.get(EMAIL_ATTRIBUTE);
    }

    @Override
    public String getAvatar() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get(
            OAUTH_ATTRIBUTES);

        if (Objects.isNull(properties)) {
            return null;
        }

        return (String) properties.get(AVATAR_ATTRIBUTE);
    }
}