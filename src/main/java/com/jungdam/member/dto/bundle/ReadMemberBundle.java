package com.jungdam.member.dto.bundle;

public class ReadMemberBundle {

    private final String oauthPermission;

    public ReadMemberBundle(String oauthPermission) {
        this.oauthPermission = oauthPermission;
    }

    public String getOauthPermission() {
        return oauthPermission;
    }
}