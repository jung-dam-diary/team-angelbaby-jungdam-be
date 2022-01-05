package com.jungdam.error.exception.token;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;

public class InvalidRefreshTokenException extends BusinessException {

    public InvalidRefreshTokenException(ErrorMessage message) {
        super(message);
    }
}
