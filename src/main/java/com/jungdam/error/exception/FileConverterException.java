package com.jungdam.error.exception;

import com.jungdam.error.BusinessException;
import com.jungdam.error.ErrorMessage;

public class FileConverterException extends BusinessException {

    public FileConverterException(ErrorMessage message) {
        super(message);
    }
}
