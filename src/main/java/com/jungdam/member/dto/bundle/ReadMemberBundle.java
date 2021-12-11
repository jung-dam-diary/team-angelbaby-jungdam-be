package com.jungdam.member.dto.bundle;

import com.jungdam.member.domain.vo.Email;

public class ReadMemberBundle {

    private final Email email;

    public ReadMemberBundle(String email) {
        this.email = new Email(email);
    }

    public Email getEmail() {
        return email;
    }
}