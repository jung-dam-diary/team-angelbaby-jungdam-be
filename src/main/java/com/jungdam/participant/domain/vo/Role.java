package com.jungdam.participant.domain.vo;

import java.util.Arrays;

public enum Role {
    OWNER("ALBUM_OWNER", "앨범 소유자"),
    ADMIN("ALBUM_ADMIN", "앨범 관리자"),
    MEMBER("ALBUM_MEMBER", "앨범 맴버"),
    ;

    private final String role;
    private final String displayRole;

    Role(String code, String displayRole) {
        this.role = code;
        this.displayRole = displayRole;
    }

    public static Role of(String role) {
        return Arrays.stream(Role.values())
            .filter(r -> r.getRole().equals(role))
            .findAny()
            .orElse(MEMBER);
    }

    public String getRole() {
        return role;
    }

    public String getDisplayRole() {
        return displayRole;
    }
}