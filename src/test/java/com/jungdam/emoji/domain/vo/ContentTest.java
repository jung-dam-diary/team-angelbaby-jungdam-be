package com.jungdam.emoji.domain.vo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.StringUtils;

class ContentTest {

    @ParameterizedTest
    @CsvSource({
        "\uD83E\uDD71C",
        "\uD83E\uDD11",
        "\uD83D\uDE1C"
    })
    void 성공_이모지_유효성_검사(String content) {
        validate(content);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "  ",
        "",
        "    "
    })
    void 실패_일기_본문_유효성_검사(String content) {
        assertThatThrownBy(() -> validate(content))
            .isInstanceOf(InvalidArgumentException.class);
    }

    private void validate(String content) {
        if (!StringUtils.hasText(content)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_DIARY_CONTENT);
        }
    }
}