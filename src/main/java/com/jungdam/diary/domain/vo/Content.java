package com.jungdam.diary.domain.vo;

import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.InvalidArgumentException;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Content {

    @Transient
    private static final int CONTENT_VALIDATOR = 0;

    @Column(name = "diary_content")
    private String content;

    protected Content() {

    }

    public Content(String content) {
        validate(content);
        this.content = content;
    }

    private void validate(String content) {
        if (Objects.isNull(content) || content.length() <= CONTENT_VALIDATOR) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_DIARY_CONTENT);
        }
    }

    public String getContent() {
        return content;
    }
}