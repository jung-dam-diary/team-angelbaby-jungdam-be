package com.jungdam.auth.oauth2.impl;

import com.jungdam.auth.oauth2.OAuth2MemberInfo;
import java.util.Map;

public class GoogleOAuth2MemberInfo extends OAuth2MemberInfo {

    private final static String OAUTH_PERMISSION_ATTRIBUTE = "sub";
    private final static String NICKNAME_ATTRIBUTE = "name";
    private final static String EMAIL_ATTRIBUTE = "email";
    private final static String AVATAR_ATTRIBUTE = "picture";

    public GoogleOAuth2MemberInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getOauthPermission() {
        return (String) attributes.get(OAUTH_PERMISSION_ATTRIBUTE);
    }

    @Override
    public String getNickname() {
        return (String) attributes.get(NICKNAME_ATTRIBUTE);
    }

    @Override
    public String getEmail() {
        return (String) attributes.get(EMAIL_ATTRIBUTE);
    }

    @Override
    public String getAvatar() {
        return (String) attributes.get(AVATAR_ATTRIBUTE);
    }
}