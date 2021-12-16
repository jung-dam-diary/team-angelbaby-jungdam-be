package com.jungdam.album.dto.response;

public class CursorResult<T> {

    private T values;
    private Boolean hasNext;

    public CursorResult(T values, Boolean hasNext) {
        this.values = values;
        this.hasNext = hasNext;
    }

    public T getValues() {
        return values;
    }

    public Boolean getHasNext() {
        return hasNext;
    }
}