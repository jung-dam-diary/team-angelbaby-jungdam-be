package com.jungdam.auth.oauth2;

import java.util.Map;

public abstract class OAuth2MemberInfo {

    protected Map<String, Object> attributes;

    public OAuth2MemberInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getOauthPermission();

    public abstract String getNickname();

    public abstract String getEmail();

    public abstract String getAvatar();
}