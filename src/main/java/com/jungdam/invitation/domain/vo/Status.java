package com.jungdam.invitation.domain.vo;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Status {
    ACCEPT("ACCEPT"),
    REJECT("REJECT"),
    PENDING("PENDING");

    private final static Logger log = LoggerFactory.getLogger(Status.class);
    private final String status;

    Status(String status) {
        this.status = status;
    }

    public static Status from(String status) {
        return Arrays.stream(values())
            .filter(s -> s.getStatus().equals(status))
            .findAny()
            .orElseThrow(
                () -> new InvalidArgumentException(ErrorMessage.INVALID_INVITATION_STATUS)
                    .error(log)
            );
    }

    public String getStatus() {
        return status;
    }
}