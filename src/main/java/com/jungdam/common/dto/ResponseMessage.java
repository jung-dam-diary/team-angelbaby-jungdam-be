package com.jungdam.common.dto;

import org.springframework.http.HttpStatus;

public enum ResponseMessage {

    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED, "회원가입 성공"),
    MEMBER_READ_SUCCESS(HttpStatus.OK, "회원조회 성공"),
    MEMBER_LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    TOKEN_REFRESH_SUCCESS(HttpStatus.OK, "토큰 리프레시 성공"),
    IMAGE_UPLOAD_SUCCESS(HttpStatus.CREATED, "이미지 업로드 성공"),
    DIARY_CREATE_SUCCESS(HttpStatus.CREATED, "일기 생성 성공"),
    DIARY_READ_SUCCESS(HttpStatus.OK, "일기 조회 성공"),
    INVITATION_CREATE_SUCCESS(HttpStatus.BAD_REQUEST, "초대 생성 성공"),

    ALBUM_CREATE_SUCCESS(HttpStatus.CREATED, "앨범 생성 성공"),
    ;

    private final HttpStatus status;
    private final String message;

    ResponseMessage(HttpStatus status, String message) {
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