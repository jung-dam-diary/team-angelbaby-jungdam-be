package com.jungdam.member.domain.vo;

import java.util.Arrays;

public enum Status {
    FREE("무료 사용자"),
    PREMIUM("프리미엄 사용자");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public static Status of(String status) {
        return Arrays.stream(Status.values())
            .filter(s -> s.getStatus().equals(status))
            .findAny()
            .orElse(FREE);
    }

    public String getStatus() {
        return status;
    }
}