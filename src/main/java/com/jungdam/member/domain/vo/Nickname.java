package com.jungdam.member.domain.vo;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import org.springframework.util.StringUtils;

@Embeddable
public class Nickname {

    @Transient
    private static final String NICKNAME_VALIDATOR = "^.{1,30}$";

    @Column(name = "member_nickname")
    private String nickname;

    protected Nickname() {
    }

    public Nickname(String nickname) {
        validate(nickname);
        this.nickname = nickname;
    }

    private void validate(String nickname) {
        if (!StringUtils.hasText(nickname) || !Pattern.matches(NICKNAME_VALIDATOR, nickname)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_MEMBER_NICKNAME);
        }
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isEquals(String nickname) {
        return this.nickname.equals(nickname);
    }
}