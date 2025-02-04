package com.jungdam.participant.domain.vo;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

@Embeddable
public class Nickname {

    private final static Logger log = LoggerFactory.getLogger(Nickname.class);

    @Transient
    private static final String NICKNAME_VALIDATOR = "^.{1,30}$";

    @Column(name = "participant_nickname")
    private String nickname;

    protected Nickname() {
    }

    public Nickname(String nickname) {
        validate(nickname);
        this.nickname = nickname;
    }

    private void validate(String nickname) {
        if (!StringUtils.hasText(nickname) || !Pattern.matches(NICKNAME_VALIDATOR, nickname)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_PARTICIPANT_NICKNAME).error(
                log);
        }
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isEquals(String nickname) {
        return this.nickname.equals(nickname);
    }
}