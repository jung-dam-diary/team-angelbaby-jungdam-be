package com.jungdam.error.exception.auth;

import com.jungdam.error.ErrorMessage;
import org.springframework.security.core.AuthenticationException;

public class FailAuthenticationException extends AuthenticationException {

    private ErrorMessage errorMessage;

    public FailAuthenticationException(String message) {
        super(message);
    }

    public FailAuthenticationException(ErrorMessage message) {
        super(message.getMessage());
        this.errorMessage = message;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}