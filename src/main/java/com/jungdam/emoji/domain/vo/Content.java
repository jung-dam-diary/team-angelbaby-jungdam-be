package com.jungdam.emoji.domain.vo;

import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.InvalidArgumentException;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.springframework.util.StringUtils;

@Embeddable
public class Content {

    @Column(name = "emoji_content")
    private String content;

    protected Content() {
    }

    public Content(String content) {
        validate(content);
        this.content = content;
    }

    private void validate(String content) {
        if (!StringUtils.hasText(content)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_EMOJI_CONTENT);
        }
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Content content1 = (Content) o;
        return Objects.equals(content, content1.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}