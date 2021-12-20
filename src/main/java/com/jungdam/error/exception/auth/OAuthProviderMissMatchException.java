package com.jungdam.error.exception.auth;

import com.jungdam.error.BusinessException;
import com.jungdam.error.ErrorMessage;

public class OAuthProviderMissMatchException extends BusinessException {

    public OAuthProviderMissMatchException(
        ErrorMessage message) {
        super(message);
    }
}