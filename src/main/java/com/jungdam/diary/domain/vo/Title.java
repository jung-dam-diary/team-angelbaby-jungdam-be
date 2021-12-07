package com.jungdam.diary.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Title {

    @Column(name = "diary_title")
    private String title;

    protected Title() {

    }

    public Title(String title) {
        this.title = title;
    }
}