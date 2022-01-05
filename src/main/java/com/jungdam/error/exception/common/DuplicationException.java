package com.jungdam.error.exception.common;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;

public class DuplicationException extends BusinessException {

    public DuplicationException(ErrorMessage message) {
        super(message);
    }
}