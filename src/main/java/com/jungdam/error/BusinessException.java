package com.jungdam.error;

public class BusinessException extends RuntimeException {

    private ErrorMessage errorMessage;

    public BusinessException(ErrorMessage message) {
        super(message.getMessage());
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}