package com.jungdam.member.dto.response;

import com.jungdam.member.domain.vo.Avatar;
import com.jungdam.member.domain.vo.Email;
import com.jungdam.member.domain.vo.Nickname;
import com.jungdam.member.domain.vo.Role;

public class ReadMemberResponse {

    private final String memberEmail;
    private final String memberNickname;
    private final String memberAvatar;
    private final String memberRole;

    public ReadMemberResponse(Email email, Nickname nickname, Avatar avatar, Role role) {
        this.memberEmail = email.getEmail();
        this.memberNickname = nickname.getNickname();
        this.memberAvatar = avatar.getAvatar();
        this.memberRole = role.getRole();
    }

    public static ReadMemberResponseBuilder builder() {
        return new ReadMemberResponseBuilder();
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public String getMemberAvatar() {
        return memberAvatar;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public static class ReadMemberResponseBuilder {

        private Email email;
        private Nickname nickname;
        private Avatar avatar;
        private Role role;

        private ReadMemberResponseBuilder() {

        }

        public ReadMemberResponseBuilder email(Email email) {
            this.email = email;
            return this;
        }

        public ReadMemberResponseBuilder nickname(Nickname nickname) {
            this.nickname = nickname;
            return this;
        }

        public ReadMemberResponseBuilder avatar(Avatar avatar) {
            this.avatar = avatar;
            return this;
        }

        public ReadMemberResponseBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public ReadMemberResponse build() {
            return new ReadMemberResponse(this.email, this.nickname, this.avatar, this.role);
        }
    }
}