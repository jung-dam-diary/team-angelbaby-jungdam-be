package com.jungdam.error.dto;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponseDto {

    private final String message;
    private final LocalDateTime serverDateTime;

    private ErrorResponseDto(String message) {
        this.message = message;
        this.serverDateTime = LocalDateTime.now();
    }

    public static ResponseEntity<ErrorResponseDto> of(HttpStatus status, ErrorMessage message) {
        return ResponseEntity
            .status(status)
            .body(new ErrorResponseDto(message.name()));
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getServerDateTime() {
        return serverDateTime;
    }
}