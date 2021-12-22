package com.jungdam.invitation.dto.bundle;

import com.jungdam.member.domain.vo.Email;

public class SearchInvitationBundle {

    private final Long memberId;
    private final Long albumId;
    private final Email email;

    public SearchInvitationBundle(Long memberId, Long albumId, String email) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.email = new Email(email);
    }

    public static SearchInvitationBundleBuilder builder() {
        return new SearchInvitationBundleBuilder();
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Email getEmail() {
        return email;
    }

    public static class SearchInvitationBundleBuilder {

        private Long memberId;
        private Long albumId;
        private String email;

        private SearchInvitationBundleBuilder() {
        }

        public SearchInvitationBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public SearchInvitationBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public SearchInvitationBundleBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public SearchInvitationBundle build() {
            return new SearchInvitationBundle(this.memberId, this.albumId, this.email);
        }
    }
}