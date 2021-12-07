package com.jungdam.common.dto;

import java.time.LocalDateTime;

public class ResponseDto<T> {

    private final String message;
    private final LocalDateTime serverDateTime;
    private final T data;

    private ResponseDto(ResponseMessage message, T data) {
        this.message = message.name();
        this.serverDateTime = LocalDateTime.now();
        this.data = data;
    }

    public static <T> ResponseDto<T> of(ResponseMessage message, T data) {
        return new ResponseDto<>(message, data);
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getServerDateTime() {
        return serverDateTime;
    }

    public T getData() {
        return data;
    }
}