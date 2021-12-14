package com.jungdam.participant.dto.response;

public class ReadParticipantResponse {

    private final String participantEmail;
    private final String participantNickname;
    private final String participantAvatar;
    private final String participantRole;

    public ReadParticipantResponse(String participantEmail, String participantNickname,
        String participantAvatar, String participantRole) {
        this.participantEmail = participantEmail;
        this.participantNickname = participantNickname;
        this.participantAvatar = participantAvatar;
        this.participantRole = participantRole;
    }

    public static ReadParticipantResponseBuilder builder() {
        return new ReadParticipantResponseBuilder();
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public String getParticipantNickname() {
        return participantNickname;
    }

    public String getParticipantAvatar() {
        return participantAvatar;
    }

    public String getParticipantRole() {
        return participantRole;
    }

    public static class ReadParticipantResponseBuilder {

        private String participantEmail;
        private String participantNickname;
        private String participantAvatar;
        private String participantRole;

        private ReadParticipantResponseBuilder() {
        }

        public ReadParticipantResponseBuilder participantEmail(final String participantEmail) {
            this.participantEmail = participantEmail;
            return this;
        }

        public ReadParticipantResponseBuilder participantNickname(
            final String participantNickname) {
            this.participantNickname = participantNickname;
            return this;
        }

        public ReadParticipantResponseBuilder participantAvatar(final String participantAvatar) {
            this.participantAvatar = participantAvatar;
            return this;
        }

        public ReadParticipantResponseBuilder participantRole(final String participantRole) {
            this.participantRole = participantRole;
            return this;
        }

        public ReadParticipantResponse build() {
            return new ReadParticipantResponse(this.participantEmail, this.participantNickname,
                this.participantAvatar, this.participantRole
            );
        }
    }

}
