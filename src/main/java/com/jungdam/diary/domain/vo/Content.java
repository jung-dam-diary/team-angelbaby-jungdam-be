package com.jungdam.diary.domain.vo;

import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.InvalidArgumentException;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Content {

    @Column(name = "diary_content")
    private String content;

    protected Content() {
    }

    public Content(String content) {
        validate(content);
        this.content = content;
    }

    private void validate(String content) {
        if (Objects.isNull(content) || content.isEmpty()) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_DIARY_CONTENT);
        }
    }

    public String getContent() {
        return content;
    }
}