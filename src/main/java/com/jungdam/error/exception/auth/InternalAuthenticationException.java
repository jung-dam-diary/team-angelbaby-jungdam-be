package com.jungdam.error.exception.auth;

import com.jungdam.error.ErrorMessage;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

public class InternalAuthenticationException extends InternalAuthenticationServiceException {

    private ErrorMessage errorMessage;

    public InternalAuthenticationException(String message) {
        super(message);
    }

    public InternalAuthenticationException(ErrorMessage message) {
        super(message.getMessage());
        this.errorMessage = message;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}