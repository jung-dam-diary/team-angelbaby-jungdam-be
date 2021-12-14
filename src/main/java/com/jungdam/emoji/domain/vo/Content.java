package com.jungdam.emoji.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Content {

    @Column(name = "emoji_content")
    private String content;

    protected Content() {
    }

    public Content(String content) {
        this.content = content;
    }
}