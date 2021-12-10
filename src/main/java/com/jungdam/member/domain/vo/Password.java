package com.jungdam.member.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Password {

    @JsonIgnore
    @Column(name = "member_password")
    private String password;

    protected Password() {

    }

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
