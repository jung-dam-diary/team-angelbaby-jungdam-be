package com.jungdam.member.domain.vo;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Email {

    @Transient
    private final static String NO_EMAIL = "NO_EMAIL";

    @Column(name = "member_email", unique = true)
    private String email;

    protected Email() {
    }

    public Email(String email) {
        this.email = validate(email);
    }

    private String validate(String email) {
        if (Objects.isNull(email)) {
            return NO_EMAIL;
        }
        return email;
    }

    public String getEmail() {
        return email;
    }
}
