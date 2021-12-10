package com.jungdam.auth.oauth2.impl;

import com.jungdam.auth.oauth2.OAuth2MemberInfo;
import java.util.Map;
import java.util.Objects;

public class NaverOAuth2MemberInfo extends OAuth2MemberInfo {

    private final static String OAUTH_ATTRIBUTES = "response";
    private final static String OAUTH_PERMISSION_ATTRIBUTE = "id";
    private final static String NICKNAME_ATTRIBUTE = "nickname";
    private final static String EMAIL_ATTRIBUTE = "email";
    private final static String AVATAR_ATTRIBUTE = "profile_image";

    public NaverOAuth2MemberInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getOauthPermission() {
        Map<String, Object> response = (Map<String, Object>) attributes.get(OAUTH_ATTRIBUTES);

        if (Objects.isNull(response)) {
            return null;
        }
        return (String) response.get(OAUTH_PERMISSION_ATTRIBUTE);
    }

    @Override
    public String getNickname() {
        Map<String, Object> response = (Map<String, Object>) attributes.get(OAUTH_ATTRIBUTES);

        if (Objects.isNull(response)) {
            return null;
        }
        return (String) response.get(NICKNAME_ATTRIBUTE);
    }

    @Override
    public String getEmail() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if (Objects.isNull(response)) {
            return null;
        }
        return (String) response.get(EMAIL_ATTRIBUTE);
    }

    @Override
    public String getAvatar() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if (Objects.isNull(response)) {
            return null;
        }
        return (String) response.get(AVATAR_ATTRIBUTE);
    }
}