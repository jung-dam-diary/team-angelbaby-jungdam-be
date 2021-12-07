package com.jungdam.diary.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Bookmark {

    @Column(name = "diary_bookmark")
    private Boolean bookmark;

    protected Bookmark() {

    }

    public Bookmark(Boolean bookmark) {
        this.bookmark = bookmark;
    }
}
