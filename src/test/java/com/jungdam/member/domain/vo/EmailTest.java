package com.jungdam.member.domain.vo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.regex.Pattern;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {

    private final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @ParameterizedTest
    @CsvSource({
        "wrjs@naver.com",
        "wrjs@google.com",
        "wrjs@jungdam.tk"
    })
    void 성공_이메일_유효성_검사(String email) {
        String actualEmail = "NO_EMAIL";
        if (Pattern.matches(EMAIL_REGEX, email)) {
            actualEmail = email;
        }
        assertThat(actualEmail).isEqualTo(email);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "wrjs@ naver.com",
        "wrjsgoogle.com",
        ""
    })
    void 실패_이메일_유효성_검사(String email) {
        String actualEmail = "NO_EMAIL";
        if (Pattern.matches(EMAIL_REGEX, email)) {
            actualEmail = email;
        }
        assertThat(actualEmail).isEqualTo("NO_EMAIL");
    }
}