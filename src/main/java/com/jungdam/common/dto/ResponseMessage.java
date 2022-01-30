package com.jungdam.common.dto;

public enum ResponseMessage {

    /**
     * AUTH RESPONSE MESSAGE
     */
    TOKEN_REFRESH_SUCCESS("R-AU-006", "토큰 리프레시 성공"),

    /**
     * MEMBER RESPONSE MESSAGE
     **/
    MEMBER_CREATE_SUCCESS("R-M-001", "회원가입 성공"),
    MEMBER_SEARCH_SUCCESS("R-M-002", "회원 검색 성공"),
    MEMBER_LOGIN_SUCCESS("R-M-003", "로그인 성공"),
    MEMBER_READ_SUCCESS("R-M-004", "프로필 조회 성공"),
    MEMBER_UPDATE_SUCCESS("R-M-005", "프로필 수정 성공"),

    /**
     * ALBUM RESPONSE MESSAGE
     **/
    ALBUM_CREATE_SUCCESS("R-A-001", "앨범 생성 성공"),
    ALBUM_READ_SUCCESS("R-A-002", "앨범 제목 및 가훈 조회 성공"),
    ALBUM_UPDATE_SUCCESS("R-A-003", "앨범 수정 성공"),
    ALBUM_READ_ALL_SUCCESS("R-A-004", "앨범 목록 조회 성공"),
    ALBUM_DELETE_SUCCESS("R-A-005", "앨범 삭제 성공"),

    /**
     * COMMENT RESPONSE MESSAGE
     **/
    COMMENT_CREATE_SUCCESS("R-C-001", "댓글 생성 성공"),
    COMMENT_READ_SUCCESS("R-C-002", "댓글 조회 성공"),
    COMMENT_UPDATE_SUCCESS("R-C-003", "댓글 수정 성공"),
    COMMENT_DELETE_SUCCESS("R-C-004", "댓글 삭제 성공"),

    /**
     * PARTICIPANT RESPONSE MESSAGE
     **/
    PARTICIPANT_CHECK_SUCCESS("R-P-001", "참여인원 확인"),
    PARTICIPANT_READ_SUCCESS("R-P-002", "멤버 리스트 조회 성공"),
    PARTICIPANT_UPDATE_NICKNAME_SUCCESS("R-P-003", "참여자 닉네임 수정 성공"),
    PARTICIPANT_ROLE_READ_SUCCESS("R-P-004", "참여자 역할 조회"),

    /**
     * DIARY RESPONSE MESSAGE
     **/
    DIARY_CREATE_SUCCESS("R-D-001", "일기 생성 성공"),
    DIARY_READ_SUCCESS("R-D-002", "일기 조회 성공"),
    DIARY_FEED_READ_ALL_SUCCESS("R-D-003", "다이어리 피드 조회 성공"),
    STORY_BOOK_READ_ALL_SUCCESS("R-D-004", "스토리북 더보기 조회 성공"),
    MOMENT_READ_ALL_SUCCESS("R-D-005", "특별한 순간 조회 성공"),
    DIARY_RECORDED_AT_CHECK_SUCCESS("R-D-006", "일기 생성날짜 검사 성공"),
    DIARY_UPDATE_SUCCESS("R-D-007", "일기 수정 성공"),
    BOOKMARK_MARK_SUCCESS("R-D-008", "일기 북마크 체크/언체크"),
    DIARY_DELETE_SUCCESS("R-D-009", "일기 삭제 성공"),
    STORY_BOOK_READ_SUCCESS("R-D-010", "스토리북 조회 성공"),

    /**
     * INVITATION RESPONSE MESSAGE
     **/
    INVITATION_CREATE_SUCCESS("R-IV-001", "초대 생성 성공"),
    INVITATION_READ_ALL_SUCCESS("R-IV-002", "초대 목록 조회 성공"),
    INVITATION_UPDATE_SUCCESS("R-IV-003", "초대 수락/거절 성공"),

    /**
     * EMOJI RESPONSE MESSAGE
     **/
    EMOJI_CREATE_AND_DELETE_SUCCESS("R-E-001", "이모지 생성 및 삭제 성공"),

    /**
     * IMAGE RESPONSE MESSAGE
     */
    IMAGE_UPLOAD_SUCCESS("R-IM-001", "이미지 업로드 성공"),

    ;

    private final String code;
    private final String message;

    ResponseMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}