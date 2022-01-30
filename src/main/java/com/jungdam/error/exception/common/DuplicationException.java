package com.jungdam.error.exception.common;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DuplicationException extends BusinessException {

    public DuplicationException(ErrorMessage message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}