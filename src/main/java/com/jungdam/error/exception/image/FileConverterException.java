package com.jungdam.error.exception.image;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.BusinessException;

public class FileConverterException extends BusinessException {

    public FileConverterException(ErrorMessage message) {
        super(message);
    }
}
