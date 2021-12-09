package com.jungdam.error;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 입력입니다."),
    NOT_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."),
    NOT_EXIST_PROVIDER_TYPE(HttpStatus.BAD_REQUEST, "존재하지 않는 제공자 타입입니다."),
    FAIL_TO_GENERATE_TOKEN(HttpStatus.BAD_REQUEST, "토큰 생성 실패"),
    NOT_EXPIRED_TOKEN_YET(HttpStatus.BAD_REQUEST, "토큰 만료일이 지나지 않았습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "토큰에 문제가 발생"),
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