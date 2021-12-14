package com.jungdam.diary.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Bookmark {

    @Column(name = "diary_bookmark")
    private Boolean bookmark = false;

    public Bookmark() {
    }

    public Boolean getBookmark() {
        return bookmark;
    }
}