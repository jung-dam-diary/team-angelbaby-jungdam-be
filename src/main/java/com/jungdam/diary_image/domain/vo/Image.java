package com.jungdam.diary_image.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Image {

    @Column(name = "diary_photo_image")
    private String image;

    protected Image() {

    }

    public Image(String image) {
        this.image = image;
    }
}