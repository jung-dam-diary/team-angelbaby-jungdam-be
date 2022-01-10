package com.jungdam.participant.domain.vo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RoleTest {

    @ParameterizedTest
    @ValueSource(strings = {"ALBUM_OWNER", "ALBUM_ADMIN", "ALBUM_MEMBER"})
    void 성공_참여자_역할_팩토리_메서드(String role) {
        Role actualRole = Arrays.stream(
                Role.values())
            .filter(r -> r.getRole().equals(role))
            .findAny()
            .orElse(Role.MEMBER);
        assertThat(actualRole.getRole()).isEqualTo(role);
    }


    @ParameterizedTest
    @ValueSource(strings = {"ALUM_OWNER", "ALBUM_ADIN", "ALBU_MEMBER"})
    void 실패_참여자_역할_팩토리_메서드(String role) {
        Role actualRole = Arrays.stream(
                Role.values())
            .filter(r -> r.getRole().equals(role))
            .findAny()
            .orElse(Role.MEMBER);
        assertThat(actualRole.getRole()).isEqualTo("ALBUM_MEMBER");
    }
}