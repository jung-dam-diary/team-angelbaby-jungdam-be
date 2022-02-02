package com.jungdam.error.exception;

import com.jungdam.error.dto.ErrorMessage;
import org.slf4j.Logger;

public class BusinessException extends RuntimeException {

    private ErrorMessage errorMessage;

    public BusinessException(ErrorMessage message) {
        super(message.getMessage());
        this.errorMessage = message;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public <T extends BusinessException> T error(Logger log) {
        log.error(getMessage(), this);
        return (T) this;
    }

    public <T extends BusinessException> T warn(Logger log) {
        log.warn(getMessage(), this);
        return (T) this;
    }
}