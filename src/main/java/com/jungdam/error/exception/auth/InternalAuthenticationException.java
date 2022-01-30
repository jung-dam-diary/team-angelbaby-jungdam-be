package com.jungdam.error.exception.auth;

import com.jungdam.error.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

public class InternalAuthenticationException extends InternalAuthenticationServiceException {

    private ErrorMessage errorMessage;
    private HttpStatus httpStatus;

    public InternalAuthenticationException(ErrorMessage message) {
        super(message.getMessage());
        this.errorMessage = message;
        this.httpStatus = HttpStatus.UNAUTHORIZED;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}