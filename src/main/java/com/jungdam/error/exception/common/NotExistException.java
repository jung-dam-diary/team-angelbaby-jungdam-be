package com.jungdam.error.exception.common;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotExistException extends BusinessException {

    public NotExistException(ErrorMessage message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
