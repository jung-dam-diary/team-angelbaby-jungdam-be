package com.jungdam.diary.domain.vo;

import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import org.springframework.util.StringUtils;

@Embeddable
public class Title {

    @Transient
    private static final int TITLE_VALIDATOR = 30;

    @Column(name = "diary_title", length = 30)
    private String title;

    protected Title() {
    }

    public Title(String title) {
        validate(title);
        this.title = title;
    }

    private void validate(String title) {
        if (!StringUtils.hasText(title) || title.length() > TITLE_VALIDATOR) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_DIARY_TITLE);
        }
    }

    public String getTitle() {
        return title;
    }
}