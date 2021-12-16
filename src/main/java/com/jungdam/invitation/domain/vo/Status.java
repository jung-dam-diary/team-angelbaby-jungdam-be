package com.jungdam.invitation.domain.vo;

import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.InvalidArgumentException;
import java.util.Arrays;

public enum Status {
    ACCEPT("ACCEPT"),
    REJECT("REJECT"),
    PENDING("PENDING");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public static Status from(String status) {
        return Arrays.stream(values())
            .filter(s -> s.getStatus().equals(status))
            .findAny()
            .orElseThrow(() -> new InvalidArgumentException(ErrorMessage.INVALID_INVITATION_STATUS));
    }

    public String getStatus() {
        return status;
    }
}