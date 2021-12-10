package com.jungdam.member.dto;

import com.jungdam.member.domain.vo.Email;

public class MemberBundle {

    public static class MemberReadBundle {

        private final Email email;

        public MemberReadBundle(String email) {
            this.email = new Email(email);
        }

        public Email getEmail() {
            return email;
        }
    }
}