package com.jungdam.member.dto.bundle;

public class ReadMemberBundle {

    private final String email;

    public ReadMemberBundle(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}