package com.jungdam.member.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Nickname {

    @Column(name = "member_nickname")
    private String nickname;

    protected Nickname() {
    }

    public Nickname(String nickname) {
        this.nickname = nickname;
    }

    //TODO 추후 Validate 로직 추가

    public String getNickname() {
        return nickname;
    }

    public boolean isEquals(String nickname) {
        return this.nickname.equals(nickname);
    }
}