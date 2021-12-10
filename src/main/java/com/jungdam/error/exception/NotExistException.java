package com.jungdam.error.exception;

import com.jungdam.error.BusinessException;
import com.jungdam.error.ErrorMessage;

public class NotExistException extends BusinessException {

    public NotExistException(ErrorMessage message) {
        super(message);
    }
}
