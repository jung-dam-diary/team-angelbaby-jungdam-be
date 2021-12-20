package com.jungdam.error.exception.token;

import com.jungdam.error.BusinessException;
import com.jungdam.error.ErrorMessage;

public class TokenValidFailedException extends BusinessException {

    public TokenValidFailedException(ErrorMessage message) {
        super(message);
    }
}
