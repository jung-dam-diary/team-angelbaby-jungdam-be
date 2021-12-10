package com.jungdam.member.domain.vo;

import java.util.Arrays;

public enum Role {
    USER("ROLE_USER", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "관리자"),
    GUEST("GUEST", "게스트");

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
            .orElse(GUEST);
    }

    public String getRole() {
        return role;
    }

    public String getDisplayRole() {
        return displayRole;
    }
}