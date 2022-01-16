package com.jungdam.diary.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.StringUtils;

class TitleTest {

    @ParameterizedTest
    @CsvSource({
        "김동건 입니다.",
        "성민수는 바보입니다.",
        "이형준는 취업성공~",
        "이준표 건축기사 가즈아~",
        "김승모 항공운항 가즈아~",
        "이승준 영국인 가즈아~"
    })
    void 성공_일기_제목_유효성_검사(String title) {
        final boolean b = !StringUtils.hasText(title) || title.length() > 30;
        assertThat(b).isEqualTo(false);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "김동건 입니다.입니다.입니다.입니다.입니다.입니다.입니다.",
        "",
        "   ",
        "이준표   건축기사 건축기사 건축기사 건축기사 건축기사 가즈아~",
        " ",
        "이승준   영국인 영국인 30글자 초과~ ㅍ영국인 가즈아~"
    })
    void 실패_일기_제목_유효성_검사(String title) {
        final boolean b = !StringUtils.hasText(title) || title.length() > 30;
        assertThat(b).isEqualTo(true);
    }
}