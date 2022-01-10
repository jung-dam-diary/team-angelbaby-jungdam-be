package com.jungdam.participant.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParticipantTest {

    @ParameterizedTest
    @CsvSource({
        "1, 1",
        "2, 2",
        "3, 3"
    })
    void 성공_앨범_동일_검사(String id1, String id2) {
        Long parseId1 = Long.parseLong(id1);
        Long parseId2 = Long.parseLong(id2);

        boolean actual = Objects.equals(parseId1, parseId2);
        assertThat(actual).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2",
        "2, 1",
        "3, 1"
    })
    void 실패_앨범_동일_검사(String id1, String id2) {
        Long parseId1 = Long.parseLong(id1);
        Long parseId2 = Long.parseLong(id2);

        boolean actual = Objects.equals(parseId1, parseId2);
        assertThat(actual).isEqualTo(false);
    }
}