package com.jungdam.album.domain.vo;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

@Embeddable
public class Title {

    private final static Logger log = LoggerFactory.getLogger(Title.class);

    @Transient
    private static final int TITLE_LENGTH = 20;

    @Column(name = "album_title", nullable = false, length = TITLE_LENGTH)
    private String title;

    protected Title() {
    }

    public Title(String title) {
        validate(title);
        this.title = title;
    }

    private void validate(String title) {
        if (!StringUtils.hasText(title) || title.length() > TITLE_LENGTH) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_ALBUM_TITLE).error(log);
        }
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Title title1 = (Title) o;

        return title.equals(title1.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}