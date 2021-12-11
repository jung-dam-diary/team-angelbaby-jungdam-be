package com.jungdam.error;

import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;

public class ErrorResponseDto {

    private final String message;
    private final LocalDateTime serverDateTime;

    private ErrorResponseDto(String message) {
        this.message = message;
        this.serverDateTime = LocalDateTime.now();
    }

    public static ResponseEntity<ErrorResponseDto> of(ErrorMessage message) {
        return ResponseEntity
            .status(
                message.getStatus()
            )
            .body(
                new ErrorResponseDto(message.getMessage())
            );
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getServerDateTime() {
        return serverDateTime;
    }
}