package com.jungdam.member.domain.vo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.StringUtils;

class AvatarTest {

    @ParameterizedTest
    @CsvSource({
        "asdkjhalsdkjh",
        "as;dkljlk sadkl;jjasd laksjdlak asd",
        "d",
        "sad",
        "1!@#$%XSAYTSDF asdFAS^SD&^SDA564ㅇ나러ㅘ라ㅣㅇㅌㄴ"
    })
    void 성공_아바타_유효성_내부_로직_검사(String avatar) {
        String actualAvatar = "NO_AVATAR";
        if (StringUtils.hasText(avatar)) {
            actualAvatar = avatar;
        }
        assertThat(actualAvatar).isEqualTo(avatar);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", ""})
    void 실패_아바타_유효성_내부_로직_검사(String avatar) {
        String actualAvatar = "NO_AVATAR";
        if (StringUtils.hasText(avatar)) {
            actualAvatar = avatar;
        }
        assertThat(actualAvatar).isEqualTo("NO_AVATAR");
    }

    @ParameterizedTest
    @CsvSource({
        "asdkjhalsdkjh",
        "as;dkljlk sadkl;jjasd laksjdlak asd",
        "d",
        "sad",
        "1!@#$%XSAYTSDF asdFAS^SD&^SDA564ㅇ나러ㅘ라ㅣㅇㅌㄴ"
    })
    void 성공_아바타_유효성_검사(String avatar) {
        Avatar instance = new Avatar(avatar);
        assertThat(instance.getAvatar()).isEqualTo(avatar);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", ""})
    void 실패_아바타_유효성_검사(String avatar) {
        Avatar instance = new Avatar(avatar);
        assertThat(instance.getAvatar()).isEqualTo("NO_AVATAR");
    }
}