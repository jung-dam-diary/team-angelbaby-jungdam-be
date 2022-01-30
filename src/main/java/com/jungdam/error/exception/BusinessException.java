package com.jungdam.error.exception;

import com.jungdam.error.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private ErrorMessage errorMessage;
    private HttpStatus httpStatus;

    public BusinessException(ErrorMessage message) {
        super(message.getMessage());
        this.errorMessage = message;
    }

    public BusinessException(final ErrorMessage message, final HttpStatus status) {
        super(message.getMessage());
        this.errorMessage = message;
        this.httpStatus = status;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}