package com.jungdam.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageUtil {

    private final static int DEFAULT_PAGE = 0;

    private PageUtil() {
    }

    public static Pageable of(int size) {
        return PageRequest.of(DEFAULT_PAGE, size);
    }
}