package com.jungdam.error.exception;

import com.jungdam.error.BusinessException;
import com.jungdam.error.ErrorMessage;

public class DuplicationException extends BusinessException {

    public DuplicationException(ErrorMessage message) {
        super(message);
    }
}