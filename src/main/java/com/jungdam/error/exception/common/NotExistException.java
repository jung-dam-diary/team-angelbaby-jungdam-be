package com.jungdam.error.exception.common;

import com.jungdam.error.BusinessException;
import com.jungdam.error.ErrorMessage;

public class NotExistException extends BusinessException {

    public NotExistException(ErrorMessage message) {
        super(message);
    }
}
