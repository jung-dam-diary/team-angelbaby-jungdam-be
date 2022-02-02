package com.jungdam.diary.domain.vo;

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
            throw new InvalidArgumentException(ErrorMessage.INVALID_DIARY_TITLE).error(log);
        }
    }

    public String getTitle() {
        return title;
    }
}