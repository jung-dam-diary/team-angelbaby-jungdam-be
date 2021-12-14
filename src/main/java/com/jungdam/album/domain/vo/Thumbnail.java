package com.jungdam.album.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Thumbnail {

    @Column(name = "album_thumbnail")
    private String thumbnail;

    protected Thumbnail() {
    }

    public Thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}