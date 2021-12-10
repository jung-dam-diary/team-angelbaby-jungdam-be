package com.jungdam.member.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Email {

    @Column(name = "member_email", unique = true)
    private String email;

    protected Email() {

    }

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
