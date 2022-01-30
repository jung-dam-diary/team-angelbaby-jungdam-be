package com.jungdam.error.exception.auth;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class OAuthProviderMissMatchException extends BusinessException {

    public OAuthProviderMissMatchException(
        ErrorMessage message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}