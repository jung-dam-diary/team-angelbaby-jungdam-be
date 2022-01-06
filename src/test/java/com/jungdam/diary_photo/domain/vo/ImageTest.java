package com.jungdam.diary_photo.domain.vo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.StringUtils;

class ImageTest {

    @ParameterizedTest
    @CsvSource({
        "asdkjhalsdkjh",
        "as;dkljlk sadkl;jjasd laksjdlak asd",
        "d",
        "sad",
        "1!@#$%XSAYTSDF asdFAS^SD&^SDA564ㅇ나러ㅘ라ㅣㅇㅌㄴ"
    })
    void 성공_이미지_유효성_검사(String image) {
        validate(image);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        " ",
        "",
        "    "
    })
    void 실패_닉네임_유효성_검사(String image) {
        assertThatThrownBy(() -> validate(image))
            .isInstanceOf(InvalidArgumentException.class);
    }

    private void validate(String image) {
        if (!StringUtils.hasText(image)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_DIARY_PHOTO_IMAGE);
        }
    }
}