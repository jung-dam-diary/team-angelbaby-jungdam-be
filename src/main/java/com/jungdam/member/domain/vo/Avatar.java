package com.jungdam.member.domain.vo;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Avatar {

    @Transient
    private final static String NO_AVATAR = "NO_AVATAR";

    @Column(name = "member_avatar")
    private String avatar;

    protected Avatar() {
    }

    public Avatar(String avatar) {
        this.avatar = validate(avatar);
    }

    private String validate(String avatar) {
        if (Objects.isNull(avatar)) {
            return NO_AVATAR;
        }
        return avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isEquals(String avatar) {
        return this.avatar.equals(avatar);
    }
}