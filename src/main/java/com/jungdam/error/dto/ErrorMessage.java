package com.jungdam.error.dto;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {

    /**
     * AUTH ERROR RESPONSE MESSAGE
     */
    FAIL_TO_GENERATE_TOKEN("E-AU-001", HttpStatus.BAD_REQUEST, "토큰 생성 실패"),
    NOT_EXPIRED_TOKEN_YET("E-AU-002", HttpStatus.BAD_REQUEST, "토큰 만료일이 지나지 않았습니다."),
    INVALID_REFRESH_TOKEN("E-AU-003", HttpStatus.BAD_REQUEST, "토큰에 문제가 발생"),
    UNAUTHORIZED_REDIRECT_URI("E-AU-004", HttpStatus.UNAUTHORIZED,
        "Unauthorized Redirect URI and can't proceed with the authentication"),
    FAIL_TO_LOGIN_OAUTH2("E-AU-005", HttpStatus.UNAUTHORIZED, "OAUTH2 로그인을 실패했습니다."),

    /**
     * MEMBER ERROR RESPONSE MESSAGE
     **/
    NOT_EXIST_PROVIDER_TYPE("E-M-007", HttpStatus.BAD_REQUEST, "존재하지 않는 제공자 타입입니다."),
    INVALID_MEMBER_NICKNAME("E-M-008", HttpStatus.BAD_REQUEST, "사용자 닉네임 정보가 잘못되었습니다."),
    NOT_EXIST_MEMBER("E-M-006", HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    ALREADY_EXIST_MEMBER_EMAIL("E-M-007", HttpStatus.BAD_REQUEST, "이미 존재하는 이메일 정보입니다."),

    /**
     * ALBUM ERROR RESPONSE MESSAGE
     **/
    INVALID_ALBUM_TITLE("E-A-001", HttpStatus.BAD_REQUEST, "형식에 맞지 않는 앨범 제목입니다."),
    INVALID_ALBUM_FAMILY_MOTTO("E-A-002", HttpStatus.BAD_REQUEST, "형식에 맞지 않는 앨범 가훈입니다."),
    NON_PERMISSION_ALBUM("E-A-003", HttpStatus.BAD_REQUEST, "앨범에 대한 권한이 없습니다."),
    INVALID_ALBUM_THUMBNAIL("E-A-004", HttpStatus.BAD_REQUEST, "형식에 맞지 않는 앨범 썸네일입니다."),
    NOT_EXIST_ALBUM("E-A-005", HttpStatus.BAD_REQUEST, "존재하지 않는 앨범입니다."),

    /**
     * COMMENT ERROR RESPONSE MESSAGE
     **/
    INVALID_COMMENT_CONTENT("E-C-002", HttpStatus.BAD_REQUEST, "댓글의 형식이 잘못되었습니다."),
    NOT_EXIST_COMMENT("E-C-001", HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다."),

    /**
     * PARTICIPANT ERROR RESPONSE MESSAGE
     **/
    DUPLICATION_PARTICIPANT_IN_ALBUM("E-P-001", HttpStatus.BAD_REQUEST, "해당 앨범에 회원이 존재합니다."),
    NOT_EXIST_PARTICIPANT("E-P-002", HttpStatus.BAD_REQUEST, "앨범에 포함되지 않은 회원입니다."),

    /**
     * COMMON ERROR RESPONSE MESSAGE
     **/
    INVALID_INPUT_VALUE("E-CM-001", HttpStatus.BAD_REQUEST, "잘못된 입력입니다."),

    /**
     * DIARY ERROR RESPONSE MESSAGE
     **/
    INVALID_DIARY_TITLE("E-D-001", HttpStatus.BAD_REQUEST, "잘못된 일기 제목입니다."),
    INVALID_DIARY_CONTENT("E-D-002", HttpStatus.BAD_REQUEST, "잘못된 일기 본문입니다."),
    INVALID_DIARY_RECORDED_AT("E-D-03", HttpStatus.BAD_REQUEST, "작성되는 날짜가 현재 날짜보다 미래입니다."),
    INVALID_DIARY_PHOTO_IMAGE("E-D-004", HttpStatus.BAD_REQUEST, "이미지 정보가 잘못되었습니다."),
    NOT_EXIST_DIARY("E-D-005", HttpStatus.BAD_REQUEST, "존재하지 않는 일기입니다."),
    DUPLICATION_DIARY_RECORDED_AT("E-D-006", HttpStatus.BAD_REQUEST, "해당 날짜에 일기가 존재합니다."),

    /**
     * INVITATION ERROR RESPONSE MESSAGE
     **/
    DUPLICATION_INVITATION_IN_ALBUM("E-IV-001", HttpStatus.BAD_REQUEST,
        "해당 앨범에 회원을 초대 후 대기 상태입니다."),
    INVALID_INVITATION_STATUS("E-IV-002", HttpStatus.BAD_REQUEST, "형식에 맞지 않는 초대 상태입니다."),
    NO_PERMISSION_INVITATION_UPDATE("E-IV-003", HttpStatus.FORBIDDEN, "초대 수정 권한이 없습니다."),

    /**
     * EMOJI ERROR RESPONSE MESSAGE
     **/
    INVALID_EMOJI_CONTENT("E-E-001", HttpStatus.NOT_FOUND, "존재하지 않는 이모지입니다."),

    /**
     * IMAGE ERROR RESPONSE MESSAGE
     **/
    FAILURE_FILE_CONVERT("E-IM-001", HttpStatus.BAD_REQUEST, "File convert fail"),

    /**
     * INTERNAL ERROR RESPONSE MESSAGE
     **/
    INTERNAL_SERVER_ERROR("E-IS-001", HttpStatus.BAD_REQUEST, "Internal Server Error"),

    ;

    private final String code;
    private final HttpStatus status;
    private final String message;

    ErrorMessage(String code, HttpStatus status, String message) {
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