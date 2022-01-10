package com.jungdam.participant.domain.vo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import java.util.regex.Pattern;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.StringUtils;

class NicknameTest {

    private final String NICKNAME_VALIDATOR = "^.{1,30}$";

    @ParameterizedTest
    @CsvSource({
        "김동건",
        "성민수",
        "이형준",
        "이준표",
        "김승모",
        "이승준"
    })
    void 성공_참여자_닉네임_유효성_검사(String nickname) {
        validate(nickname);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "김동건김동건김동건김동건김동건김동건김동건김동건김동건김동건김동건김동건김동건김동건김동건김동건",
        "",
        "    "
    })
    void 실패_참여자_닉네임_유효성_검사(String nickname) {
        assertThatThrownBy(() -> validate(nickname))
            .isInstanceOf(InvalidArgumentException.class);
    }

    private void validate(String nickname) {
        if (!StringUtils.hasText(nickname) || !Pattern.matches(NICKNAME_VALIDATOR, nickname)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_MEMBER_NICKNAME);
        }
    }
}