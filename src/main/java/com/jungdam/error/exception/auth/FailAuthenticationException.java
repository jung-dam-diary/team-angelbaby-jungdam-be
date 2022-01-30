package com.jungdam.error.exception.auth;

import com.jungdam.error.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class FailAuthenticationException extends AuthenticationException {

    private ErrorMessage errorMessage;
    private HttpStatus httpStatus;

    public FailAuthenticationException(ErrorMessage message) {
        super(message.getMessage());
        this.errorMessage = message;
        this.httpStatus = HttpStatus.UNAUTHORIZED;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}