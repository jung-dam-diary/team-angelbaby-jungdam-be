package com.jungdam.member.dto.response;

public class SearchMemberResponse {

    private final Long memberId;
    private final String email;
    private final String nickname;
    private final String avatar;

    public SearchMemberResponse(Long memberId, String email, String nickname,
        String avatar) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public static SearchMemberResponseBuilder builder() {
        return new SearchMemberResponseBuilder();
    }

    public Long getMemberId() {
        return memberId;
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

    public static class SearchMemberResponseBuilder {

        private Long memberId;
        private String email;
        private String nickname;
        private String avatar;

        private SearchMemberResponseBuilder() {
        }

        public SearchMemberResponseBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
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

        public SearchMemberResponse build() {
            return new SearchMemberResponse(this.memberId, this.email, this.nickname, this.avatar);
        }
    }
}
