package com.jungdam.member.dto.bundle;

import com.jungdam.member.domain.vo.Email;

public class SearchMemberBundle {

    private final Email email;

    public SearchMemberBundle(String email) {
        this.email = new Email(email);
    }

    public Email getEmail() {
        return email;
    }
}
