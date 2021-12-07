package com.jungdam.diary.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Content {

    @Column(name = "diary_content")
    private String content;

    protected Content() {

    }

    public Content(String content) {
        this.content = content;
    }
}