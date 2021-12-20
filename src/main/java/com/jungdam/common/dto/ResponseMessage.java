package com.jungdam.common.dto;

import org.springframework.http.HttpStatus;

public enum ResponseMessage {

    /**
     * AUTH RESPONSE MESSAGE
     */
    TOKEN_REFRESH_SUCCESS("AU006", HttpStatus.OK, "토큰 리프레시 성공"),

    /**
     * MEMBER RESPONSE MESSAGE
     **/
    MEMBER_CREATE_SUCCESS("M001", HttpStatus.CREATED, "회원가입 성공"),
    MEMBER_SEARCH_SUCCESS("M002", HttpStatus.OK, "회원 검색 성공"),
    MEMBER_LOGIN_SUCCESS("M003", HttpStatus.OK, "로그인 성공"),
    MEMBER_READ_SUCCESS("M004", HttpStatus.OK, "프로필 조회 성공"),
    MEMBER_UPDATE_SUCCESS("M005", HttpStatus.OK, "프로필 수정 성공"),

    /**
     * ALBUM RESPONSE MESSAGE
     **/
    ALBUM_CREATE_SUCCESS("A001", HttpStatus.CREATED, "앨범 생성 성공"),
    ALBUM_READ_SUCCESS("A002", HttpStatus.OK, "앨범 제목 및 가훈 조회 성공"),
    ALBUM_UPDATE_SUCCESS("A003", HttpStatus.OK, "앨범 수정 성공"),
    ALBUM_READ_ALL_SUCCESS("A004", HttpStatus.OK, "앨범 목록 조회 성공"),
    ALBUM_DELETE_SUCCESS("A005", HttpStatus.OK, "앨범 삭제 성공"),

    /**
     * COMMENT RESPONSE MESSAGE
     **/
    COMMENT_CREATE_SUCCESS("C001", HttpStatus.CREATED, "댓글 생성 성공"),
    COMMENT_READ_SUCCESS("C002", HttpStatus.OK, "댓글 조회 성공"),
    COMMENT_UPDATE_SUCCESS("C003", HttpStatus.OK, "댓글 수정 성공"),
    COMMENT_DELETE_SUCCESS("C004", HttpStatus.OK, "댓글 삭제 성공"),

    /**
     * PARTICIPANT RESPONSE MESSAGE
     **/
    PARTICIPANT_CHECK_SUCCESS("P001", HttpStatus.OK, "참여인원 확인"),
    PARTICIPANT_READ_SUCCESS("P002", HttpStatus.OK, "멤버 리스트 조회 성공"),
    PARTICIPANT_UPDATE_NICKNAME_SUCCESS("P003", HttpStatus.OK, "참여자 닉네임 수정 성공"),

    /**
     * DIARY RESPONSE MESSAGE
     **/
    DIARY_CREATE_SUCCESS("D001", HttpStatus.CREATED, "일기 생성 성공"),
    DIARY_READ_SUCCESS("D002", HttpStatus.OK, "일기 조회 성공"),
    DIARY_FEED_READ_ALL_SUCCESS("D003", HttpStatus.OK, "다이어리 피드 조회 성공"),
    STORY_BOOK_READ_ALL_SUCCESS("D004", HttpStatus.OK, "스토리북 더보기 조회 성공"),
    MOMENT_READ_ALL_SUCCESS("D005", HttpStatus.OK, "특별한 순간 조회 성공"),
    DIARY_RECORDED_AT_CHECK_SUCCESS("D006", HttpStatus.OK, "일기 생성날짜 검사 성공"),
    DIARY_UPDATE_SUCCESS("D007", HttpStatus.OK, "일기 수정 성공"),
    BOOKMARK_MARK_SUCCESS("D008", HttpStatus.OK, "일기 북마크 체크/언체크"),
    DIARY_DELETE_SUCCESS("D009", HttpStatus.OK, "일기 삭제 성공"),
    STORY_BOOK_READ_SUCCESS("D010",HttpStatus.OK, "스토리북 조회 성공"),

    /**
     * INVITATION RESPONSE MESSAGE
     **/
    INVITATION_CREATE_SUCCESS("IV001", HttpStatus.CREATED, "초대 생성 성공"),
    INVITATION_READ_ALL_SUCCESS("IV002", HttpStatus.OK, "초대 목록 조회 성공"),
    INVITATION_UPDATE_SUCCESS("IV003", HttpStatus.OK, "초대 수락/거절 성공"),

    /**
     * EMOJI RESPONSE MESSAGE
     **/
    EMOJI_CREATE_AND_DELETE_SUCCESS("E001", HttpStatus.OK, "이모지 생성 및 삭제 성공"),

    /**
     * IMAGE RESPONSE MESSAGE
     */
    IMAGE_UPLOAD_SUCCESS("IM001", HttpStatus.CREATED, "이미지 업로드 성공"),

    ;

    private final String code;
    private final HttpStatus status;
    private final String message;

    ResponseMessage(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}