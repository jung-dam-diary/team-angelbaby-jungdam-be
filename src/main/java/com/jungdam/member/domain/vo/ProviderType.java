package com.jungdam.member.domain.vo;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.auth.OAuthProviderMissMatchException;
import java.util.Arrays;

public enum ProviderType {
    GOOGLE,
    NAVER,
    KAKAO,
    LOCAL;

    ProviderType() {
    }

    public static ProviderType of(String type) {
        return Arrays.stream(ProviderType.values())
            .filter(p -> isEquals(type, p))
            .findAny()
            .orElseThrow(
                () -> new OAuthProviderMissMatchException(ErrorMessage.NOT_EXIST_PROVIDER_TYPE)
            );
    }

    private static boolean isEquals(String type, ProviderType providerType) {
        return providerType.name().equals(type);
    }
}