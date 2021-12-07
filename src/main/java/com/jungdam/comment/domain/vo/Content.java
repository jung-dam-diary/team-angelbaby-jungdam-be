package com.jungdam.comment.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Content {

    @Column(name = "comment_content", length = 500)
    private String content;

    protected Content() {

    }

    public Content(String content) {
        this.content = content;
    }
}