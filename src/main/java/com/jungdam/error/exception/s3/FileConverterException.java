package com.jungdam.error.exception.s3;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class FileConverterException extends BusinessException {

    public FileConverterException(ErrorMessage message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
