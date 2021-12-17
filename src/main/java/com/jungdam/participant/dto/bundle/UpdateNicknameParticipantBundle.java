package com.jungdam.participant.dto.bundle;

import com.jungdam.participant.domain.vo.Nickname;
import com.jungdam.participant.dto.request.UpdateNicknameParticipantRequest;

public class UpdateNicknameParticipantBundle {

    private final Long albumId;
    private final Long memberId;
    private final Nickname nickname;

    public UpdateNicknameParticipantBundle(Long albumId, Long memberId,
        UpdateNicknameParticipantRequest request) {
        this.albumId = albumId;
        this.memberId = memberId;
        this.nickname = new Nickname(request.getNickname());
    }

    public static UpdateNicknameParticipantBundleBuilder builder() {
        return new UpdateNicknameParticipantBundleBuilder();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public static class UpdateNicknameParticipantBundleBuilder {

        private Long albumId;
        private Long memberId;
        private UpdateNicknameParticipantRequest request;

        private UpdateNicknameParticipantBundleBuilder() {
        }

        public UpdateNicknameParticipantBundleBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public UpdateNicknameParticipantBundleBuilder memberId(final Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public UpdateNicknameParticipantBundleBuilder request(
            final UpdateNicknameParticipantRequest request) {
            this.request = request;
            return this;
        }

        public UpdateNicknameParticipantBundle build() {
            return new UpdateNicknameParticipantBundle(albumId, memberId, request);
        }
    }
}