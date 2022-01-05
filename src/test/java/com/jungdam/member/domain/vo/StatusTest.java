package com.jungdam.member.domain.vo;

import static com.jungdam.member.domain.vo.Status.FREE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StatusTest {

    @ParameterizedTest
    @ValueSource(strings = {"무료 사용자", "프리미엄 사용자"})
    void 성공_사용자_등급_상태_팩토리_메서드(String status) {
        Status actual = Arrays.stream(Status.values())
            .filter(s -> s.getStatus().equals(status))
            .findAny()
            .orElse(FREE);
        assertThat(actual.getStatus()).isEqualTo(status);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "OLE_ADMIN", "G프UST", ""})
    void 실패_사용자_등급_상태_팩토리_메서드(String status) {
        Status actual = Arrays.stream(Status.values())
            .filter(s -> s.getStatus().equals(status))
            .findAny()
            .orElse(FREE);
        assertThat(actual).isEqualTo(FREE);
    }
}