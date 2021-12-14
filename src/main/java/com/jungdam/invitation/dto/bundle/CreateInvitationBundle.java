package com.jungdam.invitation.dto.bundle;

public class CreateInvitationBundle {

    private final Long targetMemberId;
    private final Long subjectMemberId;
    private final Long albumId;

    public CreateInvitationBundle(Long targetMemberId, Long subjectMemberId, Long albumId) {
        this.targetMemberId = targetMemberId;
        this.subjectMemberId = subjectMemberId;
        this.albumId = albumId;
    }

    public static CreateInvitationBundle.CreateInvitationBundleBuilder builder() {
        return new CreateInvitationBundle.CreateInvitationBundleBuilder();
    }

    public Long getTargetMemberId() {
        return targetMemberId;
    }

    public Long getSubjectMemberId() {
        return subjectMemberId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public static class CreateInvitationBundleBuilder {

        private Long targetMemberId;
        private Long subjectMemberId;
        private Long albumId;

        private CreateInvitationBundleBuilder() {

        }

        public CreateInvitationBundle.CreateInvitationBundleBuilder targetMemberId(
            final Long targetMemberId) {
            this.targetMemberId = targetMemberId;
            return this;
        }

        public CreateInvitationBundle.CreateInvitationBundleBuilder subjectMemberId(
            final Long subjectMemberId) {
            this.subjectMemberId = subjectMemberId;
            return this;
        }

        public CreateInvitationBundle.CreateInvitationBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public CreateInvitationBundle build() {
            return new CreateInvitationBundle(this.targetMemberId, this.subjectMemberId,
                this.albumId);
        }
    }
}
