package com.jungdam.diary_photo.domain.vo;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.springframework.util.StringUtils;

@Embeddable
public class Image {

    @Column(name = "diary_photo_image")
    private String image;

    protected Image() {
    }

    public Image(String image) {
        validate(image);
        this.image = image;
    }

    private void validate(String image) {
        if (!StringUtils.hasText(image)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_DIARY_PHOTO_IMAGE);
        }
    }

    public String getImage() {
        return image;
    }
}