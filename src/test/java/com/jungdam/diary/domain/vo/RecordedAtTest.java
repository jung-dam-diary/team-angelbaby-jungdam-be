package com.jungdam.diary.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RecordedAtTest {


    @ParameterizedTest
    @CsvSource({
        "2022-01-16",
        "2002-01-16",
        "2020-01-16",
        "2020-01-17"
    })
    void 성공_기록날짜_유효성_검사(LocalDate localDate) {
        final boolean after = localDate.isAfter(LocalDate.now());
        assertThat(after).isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({
        "2022-01-18",
        "2022-01-19",
        "2022-01-20"
    })
    void 실패_기록날짜_유효성_검사(LocalDate localDate) {
        final boolean after = localDate.isAfter(LocalDate.now());
        assertThat(after).isEqualTo(true);
    }
}