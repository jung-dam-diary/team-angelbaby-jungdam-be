package com.jungdam.album.domain.vo;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.springframework.util.StringUtils;

@Embeddable
public class Thumbnail {

    @Column(name = "album_thumbnail", nullable = false)
    private String thumbnail;

    protected Thumbnail() {
    }

    public Thumbnail(String thumbnail) {
        validate(thumbnail);
        this.thumbnail = thumbnail;
    }

    // TODO: URL 유효성 검증 로직 추가 (팀 협의 후 적용)
    private void validate(String thumbnail) {
        if (!StringUtils.hasText(thumbnail)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_ALBUM_THUMBNAIL);
        }
    }

    public String getThumbnail() {
        return thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Thumbnail thumbnail1 = (Thumbnail) o;

        return thumbnail.equals(thumbnail1.thumbnail);
    }

    @Override
    public int hashCode() {
        return thumbnail.hashCode();
    }
}