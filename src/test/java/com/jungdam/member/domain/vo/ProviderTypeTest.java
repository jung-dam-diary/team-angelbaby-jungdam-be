package com.jungdam.member.domain.vo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.jungdam.error.exception.auth.OAuthProviderMissMatchException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ProviderTypeTest {

    @ParameterizedTest
    @CsvSource({
        "GOOGLE",
        "NAVER",
        "KAKAO",
        "LOCAL"
    })
    void 성공_프로바이더_팩토리_메서드(String type) {
        ProviderType providerType = ProviderType.of(type);
        assertThat(providerType.name()).isEqualTo(type);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        " R",
        "KA AO",
        "LOCA"
    })
    void 실패_프로바이더_팩토리_메서드(String type) {
        assertThatThrownBy(() -> ProviderType.of(type))
            .isInstanceOf(OAuthProviderMissMatchException.class);
    }
}