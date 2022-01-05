package com.jungdam.member.domain.vo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RoleTest {

    @ParameterizedTest
    @ValueSource(strings = {"ROLE_USER", "ROLE_ADMIN", "GUEST"})
    void 성공_사용자_역할_팩토리_메서드(String role) {
        Role actualRole = Arrays.stream(Role.values())
            .filter(r -> r.getRole().equals(role))
            .findAny()
            .orElse(Role.GUEST);
        assertThat(actualRole.getRole()).isEqualTo(role);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "OLE_ADMIN", "GUST", ""})
    void 실패_사용자_역할_팩토리_메서드(String role) {
        Role actualRole = Arrays.stream(Role.values())
            .filter(r -> r.getRole().equals(role))
            .findAny()
            .orElse(Role.GUEST);
        assertThat(actualRole.getRole()).isEqualTo("GUEST");
    }
}