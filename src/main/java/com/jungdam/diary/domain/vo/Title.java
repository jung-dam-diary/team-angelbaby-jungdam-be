package com.jungdam.diary.domain.vo;

import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.InvalidArgumentException;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Title {

    @Transient
    private static final String TITLE_VALIDATOR = "^.{1,30}$";

    @Column(name = "diary_title", length = 30)
    private String title;

    protected Title() {

    }

    public Title(String title) {
        validate(title);
        this.title = title;
    }

    private void validate(String title) {
        if (!Pattern.matches(TITLE_VALIDATOR, title) || !title.trim().isEmpty()) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_DIARY_TITLE);
        }
    }

    public String getTitle() {
        return title;
    }
}