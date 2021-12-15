package com.jungdam.member.dto.response;

public class ReadMemberResponse {

    private final String memberEmail;
    private final String memberNickname;
    private final String memberAvatar;
    private final String memberRole;

    public ReadMemberResponse(String memberEmail, String memberNickname, String memberAvatar,
        String memberRole) {
        this.memberEmail = memberEmail;
        this.memberNickname = memberNickname;
        this.memberAvatar = memberAvatar;
        this.memberRole = memberRole;
    }

    public static SearchMemberResponseBuilder builder() {
        return new SearchMemberResponseBuilder();
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

    public static class SearchMemberResponseBuilder {

        private String email;
        private String nickname;
        private String avatar;
        private String role;

        private SearchMemberResponseBuilder() {

        }

        public SearchMemberResponseBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public SearchMemberResponseBuilder nickname(final String nickname) {
            this.nickname = nickname;
            return this;
        }

        public SearchMemberResponseBuilder avatar(final String avatar) {
            this.avatar = avatar;
            return this;
        }

        public SearchMemberResponseBuilder role(final String role) {
            this.role = role;
            return this;
        }

        public ReadMemberResponse build() {
            return new ReadMemberResponse(this.email, this.nickname, this.avatar, this.role);
        }
    }
}