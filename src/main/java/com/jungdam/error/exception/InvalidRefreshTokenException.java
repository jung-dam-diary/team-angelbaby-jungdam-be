package com.jungdam.error.exception;

import com.jungdam.error.BusinessException;
import com.jungdam.error.ErrorMessage;

public class InvalidRefreshTokenException extends BusinessException {

    public InvalidRefreshTokenException(ErrorMessage message) {
        super(message);
    }
}
