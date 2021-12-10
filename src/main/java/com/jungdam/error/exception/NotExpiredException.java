package com.jungdam.error.exception;

import com.jungdam.error.BusinessException;
import com.jungdam.error.ErrorMessage;

public class NotExpiredException extends BusinessException {

    public NotExpiredException(ErrorMessage message) {
        super(message);
    }
}
