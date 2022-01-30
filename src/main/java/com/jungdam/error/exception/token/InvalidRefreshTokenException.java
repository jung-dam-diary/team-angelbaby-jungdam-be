package com.jungdam.error.exception.token;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends BusinessException {

    public InvalidRefreshTokenException(ErrorMessage message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
