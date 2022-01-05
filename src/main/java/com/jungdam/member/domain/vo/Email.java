package com.jungdam.member.domain.vo;

import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Email {

    @Transient
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

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
        if (Pattern.matches(EMAIL_REGEX, email)) {
            return email;
        }
        return NO_EMAIL;
    }

    public String getEmail() {
        return email;
    }
}