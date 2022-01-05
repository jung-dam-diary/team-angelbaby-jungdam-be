package com.jungdam.comment.domain.vo;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import org.springframework.util.StringUtils;

@Embeddable
public class Content {

    @Transient
    private static final int CONTENT_VALIDATOR = 500;

    @Column(name = "comment_content", length = 500)
    private String content;

    protected Content() {
    }

    public Content(String content) {
        validate(content);
        this.content = content;
    }

    private void validate(String content) {
        if (!StringUtils.hasText(content) || content.length() > CONTENT_VALIDATOR) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_COMMENT_CONTENT);
        }
    }

    public String getContent() {
        return content;
    }
}