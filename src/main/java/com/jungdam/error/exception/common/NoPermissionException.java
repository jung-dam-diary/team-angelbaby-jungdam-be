package com.jungdam.error.exception.common;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;

public class NoPermissionException extends BusinessException {

    public NoPermissionException(ErrorMessage message) {
        super(message);
    }
}