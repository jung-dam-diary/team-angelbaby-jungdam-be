package com.jungdam.error;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 입력입니다."),
    FAILURE_FILE_CONVERT(HttpStatus.BAD_REQUEST, "File convert fail");

    private final HttpStatus status;
    private final String message;

    ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}