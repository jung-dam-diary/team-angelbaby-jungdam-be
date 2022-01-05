package com.jungdam.error.exception.token;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;

public class TokenValidFailedException extends BusinessException {

    public TokenValidFailedException(ErrorMessage message) {
        super(message);
    }
}
