package com.jungdam.common.dto;

import org.springframework.http.HttpStatus;

public enum ResponseMessage {

    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED, "회원가입 성공"),
    MEMBER_LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    MEMBER_READ_SUCCESS(HttpStatus.OK, "프로필 조회 성공"),
    MEMBER_UPDATE_SUCCESS(HttpStatus.OK, "프로필 수정 성공"),
    TOKEN_REFRESH_SUCCESS(HttpStatus.OK, "토큰 리프레시 성공"),
    IMAGE_UPLOAD_SUCCESS(HttpStatus.CREATED, "이미지 업로드 성공"),
    DIARY_CREATE_SUCCESS(HttpStatus.CREATED, "일기 생성 성공"),
    INVITATION_CREATE_SUCCESS(HttpStatus.CREATED, "초대 생성 성공"),
    INVITATION_READ_ALL_SUCCESS(HttpStatus.OK, "초대 목록 조회 성공"),
    INVITATION_UPDATE_SUCCESS(HttpStatus.OK, "초대 수락/거절 성공"),
    PARTICIPANT_READ_SUCCESS(HttpStatus.OK, "멤버 리스트 조회 성공"),
    DIARY_READ_SUCCESS(HttpStatus.OK, "일기 조회 성공"),
    COMMENT_CREATE_SUCCESS(HttpStatus.CREATED, "댓글 생성 성공"),
    ALBUM_CREATE_SUCCESS(HttpStatus.CREATED, "앨범 생성 성공"),
    MEMBER_SEARCH_SUCCESS(HttpStatus.OK, "회원 검색 성공"),
    ALBUM_READ_SUCCESS(HttpStatus.OK, "앨범 제목 및 가훈 조회 성공"),
    BOOKMARK_MARK_SUCCESS(HttpStatus.OK, "일기 북마크 체크/언체크"),
    ALBUM_DELETE_SUCCESS(HttpStatus.OK, "앨범 삭제 성공"),
    ALBUM_UPDATE_SUCCESS(HttpStatus.OK, "앨범 수정 성공"),
    COMMENT_DELETE_SUCCESS(HttpStatus.OK, "댓글 삭제 성공"),
    DIARY_DELETE_SUCCESS(HttpStatus.OK, "일기 삭제 성공"),
    DIARY_RECORDED_AT_CHECK_SUCCESS(HttpStatus.OK, "일기 생성날짜 검사 성공"),
    COMMENT_READ_SUCCESS(HttpStatus.OK, "댓글 조회 성공"),
    PARTICIPANT_CHECK_SUCCESS(HttpStatus.OK, "참여인원 확인"),
    COMMENT_UPDATE_SUCCESS(HttpStatus.OK, "댓글 수정 성공"),

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