package com.jungdam.error.exception.common;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NoPermissionException extends BusinessException {

    public NoPermissionException(ErrorMessage message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}