package com.jungdam.common.dto;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseDto<T> {

    private final String message;
    private final LocalDateTime serverDateTime;
    private final T data;

    private ResponseDto(ResponseMessage message, T data) {
        this.message = message.name();
        this.serverDateTime = LocalDateTime.now();
        this.data = data;
    }
    
    public static <T> ResponseEntity<ResponseDto<T>> ok(ResponseMessage message, T data) {
        return ResponseEntity
            .ok(new ResponseDto<>(message, data));
    }

    public static <T> ResponseEntity<ResponseDto<T>> created(ResponseMessage message, T data) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto<>(message, data));
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