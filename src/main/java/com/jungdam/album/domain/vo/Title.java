package com.jungdam.album.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Title {

    @Column(name = "album_title", length = 20)
    private String title;

    protected Title() {

    }

    public Title(String title) {
        this.title = title;
    }
}