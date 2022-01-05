package com.jungdam.error.exception.token;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;

public class NotExpiredException extends BusinessException {

    public NotExpiredException(ErrorMessage message) {
        super(message);
    }
}
