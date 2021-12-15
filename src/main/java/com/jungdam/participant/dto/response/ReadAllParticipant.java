package com.jungdam.participant.dto.response;

public class ReadAllParticipant {

    private final String email;
    private final String nickname;
    private final String avatar;
    private final String role;

    public ReadAllParticipant(String email, String nickname, String avatar, String role) {
        this.email = email;
        this.nickname = nickname;
        this.avatar = avatar;
        this.role = role;
    }

    public static ReadParticipantResponseBuilder builder() {
        return new ReadParticipantResponseBuilder();
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getRole() {
        return role;
    }

    public static class ReadParticipantResponseBuilder {

        private String email;
        private String nickname;
        private String avatar;
        private String role;

        private ReadParticipantResponseBuilder() {
        }

        public ReadParticipantResponseBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public ReadParticipantResponseBuilder nickname(final String nickname) {
            this.nickname = nickname;
            return this;
        }

        public ReadParticipantResponseBuilder avatar(final String avatar) {
            this.avatar = avatar;
            return this;
        }

        public ReadParticipantResponseBuilder role(final String role) {
            this.role = role;
            return this;
        }

        public ReadAllParticipant build() {
            return new ReadAllParticipant(this.email, this.nickname, this.avatar, this.role);
        }
    }

}
