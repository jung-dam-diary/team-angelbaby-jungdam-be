package com.jungdam.comment.domain.vo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.StringUtils;

class ContentTest {

    private final int CONTENT_VALIDATOR = 500;


    @ParameterizedTest
    @CsvSource({
        "mfdkjghkfhgkjhdgfghlkdhfkgj",
        "l df",
        "assdas  2 1  3 D S F #$%T"
    })
    void 성공_댓글_유효성_검사(String comment) {
        validate(comment);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        " ",
        "",
        "    ",
        "saddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "saddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
            + "dddddddddddddddddddddddddddddddddddddddddddddddddddd"
    })
    void 실패_댓글_유효성_검사(String comment) {
        assertThatThrownBy(() -> validate(comment))
            .isInstanceOf(InvalidArgumentException.class);
    }

    private void validate(String content) {
        if (!StringUtils.hasText(content) || content.length() > CONTENT_VALIDATOR) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_COMMENT_CONTENT);
        }
    }
}