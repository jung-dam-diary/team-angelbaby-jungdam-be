package com.jungdam.member.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Avatar {

    @Column(name = "member_avatar")
    private String avatar;

    protected Avatar() {

    }

    public Avatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }
}