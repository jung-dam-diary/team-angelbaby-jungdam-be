package com.jungdam.diary.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Bookmark {

    @Column(name = "diary_bookmark")
    private Boolean bookmark = false;

    public Bookmark() {
    }

    public void check() {
        if (this.bookmark) {
            this.bookmark = false;
            return;
        }
        this.bookmark = true;
    }
}