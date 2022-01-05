package com.jungdam.error.exception;

import com.jungdam.error.dto.ErrorMessage;

public class BusinessException extends RuntimeException {

    private ErrorMessage errorMessage;

    public BusinessException(ErrorMessage message) {
        super(message.getMessage());
        this.errorMessage = message;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}