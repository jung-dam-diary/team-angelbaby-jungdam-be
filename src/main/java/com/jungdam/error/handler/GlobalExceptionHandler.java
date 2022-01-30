package com.jungdam.error.handler;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.dto.ErrorResponseDto;
import com.jungdam.error.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponseDto> handleBusinessException(
        BusinessException exception) {
        HttpStatus status = exception.getHttpStatus();
        ErrorMessage message = exception.getErrorMessage();
        return ErrorResponseDto.of(status, message);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseDto> handleException() {
        return ErrorResponseDto.of(
            HttpStatus.INTERNAL_SERVER_ERROR,
            ErrorMessage.INTERNAL_SERVER_ERROR
        );
    }
}