package com.jungdam.error.exception.auth;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;

public class OAuthProviderMissMatchException extends BusinessException {

    public OAuthProviderMissMatchException(
        ErrorMessage message) {
        super(message);
    }
}