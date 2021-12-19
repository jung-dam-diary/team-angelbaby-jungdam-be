package com.jungdam.participant.dto.response;

public class ParticipantInfosResponse {

    private final String email;
    private final String nickname;
    private final String avatar;

    public ParticipantInfosResponse(String email, String nickname, String avatar) {
        this.email = email;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public static ParticipantInfosResponseBuilder builder() {
        return new ParticipantInfosResponseBuilder();
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

    public static class ParticipantInfosResponseBuilder {

        private String email;
        private String nickname;
        private String avatar;

        private ParticipantInfosResponseBuilder() {
        }

        public ParticipantInfosResponseBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public ParticipantInfosResponseBuilder nickname(final String nickname) {
            this.nickname = nickname;
            return this;
        }

        public ParticipantInfosResponseBuilder avatar(final String avatar) {
            this.avatar = avatar;
            return this;
        }

        public ParticipantInfosResponse build() {
            return new ParticipantInfosResponse(this.email, this.nickname, this.avatar);
        }
    }
}