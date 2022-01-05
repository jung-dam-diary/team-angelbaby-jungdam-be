package com.jungdam.error.exception.common;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;

public class InvalidArgumentException extends BusinessException {

    public InvalidArgumentException(ErrorMessage message) {
        super(message);
    }
}