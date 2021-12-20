package com.jungdam.error;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {

    /**
     * AUTH EXCEPTION
     **/
    FAIL_TO_GENERATE_TOKEN("A001", HttpStatus.BAD_REQUEST, "토큰 생성 실패"),
    NOT_EXPIRED_TOKEN_YET("A002", HttpStatus.BAD_REQUEST, "토큰 만료일이 지나지 않았습니다."),
    INVALID_REFRESH_TOKEN("A003", HttpStatus.BAD_REQUEST, "토큰에 문제가 발생"),
    UNAUTHORIZED_REDIRECT_URI("A004", HttpStatus.UNAUTHORIZED,
        "Unauthorized Redirect URI and can't proceed with the authentication"),
    FAIL_TO_LOGIN_OAUTH2("A005", HttpStatus.UNAUTHORIZED, "OAUTH2 로그인을 실패했습니다."),
    NOT_EXIST_MEMBER("A006", HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),

    /**
     * FILE EXCEPTION
     **/
    FAILURE_FILE_CONVERT("S001", HttpStatus.BAD_REQUEST, "File convert fail"),

    /**
     * COMMON EXCEPTION
     **/
    INVALID_INPUT_VALUE("C001", HttpStatus.BAD_REQUEST, "잘못된 입력입니다."),
    NO_PERMISSION_INVITATION_UPDATE("C002", HttpStatus.FORBIDDEN, "초대 수정 권한이 없습니다."),
    NOT_EXIST_PROVIDER_TYPE("C003", HttpStatus.BAD_REQUEST, "존재하지 않는 제공자 타입입니다."),
    INVALID_DIARY_TITLE("C004", HttpStatus.BAD_REQUEST, "잘못된 일기 제목입니다."),
    INVALID_DIARY_CONTENT("C005", HttpStatus.BAD_REQUEST, "잘못된 일기 본문입니다."),
    NOT_EXIST_ALBUM("C006", HttpStatus.BAD_REQUEST, "존재하지 않는 앨범입니다."),
    DUPLICATION_DIARY_RECORDED_AT("C007", HttpStatus.BAD_REQUEST, "해당 날짜에 일기가 존재합니다."),
    NOT_EXIST_PARTICIPANT("C008", HttpStatus.BAD_REQUEST, "앨범에 포함되지 않은 회원입니다."),
    NOT_EXIST_DIARY("C009", HttpStatus.BAD_REQUEST, "존재하지 않는 일기입니다."),
    DUPLICATION_PARTICIPANT_IN_ALBUM("C010", HttpStatus.BAD_REQUEST, "해당 앨범에 회원이 존재합니다."),
    DUPLICATION_INVITATION_IN_ALBUM("C011", HttpStatus.BAD_REQUEST, "해당 앨범에 회원을 초대 후 대기 상태입니다."),
    INVALID_INVITATION_STATUS("C012", HttpStatus.BAD_REQUEST, "형식에 맞지 않는 초대 상태입니다."),
    INVALID_COMMENT_CONTENT("C013", HttpStatus.BAD_REQUEST, "댓글의 형식이 잘못되었습니다."),
    INVALID_DIARY_PHOTO_IMAGE("C014", HttpStatus.BAD_REQUEST, "이미지 정보가 잘못되었습니다."),
    INVALID_ALBUM_TITLE("C015", HttpStatus.BAD_REQUEST, "형식에 맞지 않는 앨범 제목입니다."),
    INVALID_ALBUM_FAMILY_MOTTO("C016", HttpStatus.BAD_REQUEST, "형식에 맞지 않는 앨범 가훈입니다."),
    INVALID_ALBUM_THUMBNAIL("C017", HttpStatus.BAD_REQUEST, "형식에 맞지 않는 앨범 썸네일입니다."),
    INVALID_DIARY_RECORDED_AT("C018", HttpStatus.BAD_REQUEST, "작성되는 날짜가 현재 날짜보다 미래입니다."),
    NON_PERMISSION_ALBUM("C019", HttpStatus.BAD_REQUEST, "앨범에 대한 권한이 없습니다."),
    NOT_EXIST_COMMENT("C020", HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다."),
    INVALID_EMOJI_CONTENT("C021", HttpStatus.NOT_FOUND, "존재하지 않는 이모지입니다."),
    INVALID_MEMBER_NICKNAME("C022", HttpStatus.BAD_REQUEST, "사용자 닉네임 정보가 잘못되었습니다."),

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