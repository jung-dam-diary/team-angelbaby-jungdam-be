package com.jungdam.member.dto;

public class MemberResponse {

    public static class MemberReadResponse {

        private final String memberEmail;
        private final String memberNickname;
        private final String memberAvatar;
        private final String memberRole;

        public MemberReadResponse(String memberEmail, String memberNickname, String memberAvatar,
            String memberRole) {
            this.memberEmail = memberEmail;
            this.memberNickname = memberNickname;
            this.memberAvatar = memberAvatar;
            this.memberRole = memberRole;
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
    }
}