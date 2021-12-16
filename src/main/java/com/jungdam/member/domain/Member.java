package com.jungdam.member.domain;

import com.jungdam.common.domain.BaseEntity;
import com.jungdam.member.domain.vo.Avatar;
import com.jungdam.member.domain.vo.Email;
import com.jungdam.member.domain.vo.Nickname;
import com.jungdam.member.domain.vo.ProviderType;
import com.jungdam.member.domain.vo.Role;
import com.jungdam.member.domain.vo.Status;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Nickname nickname;

    @Embedded
    private Avatar avatar;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "member_status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "member_provider_type")
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(name = "member_oauth_permission", unique = true)
    private String oauthPermission;

    protected Member() {
    }

    public Member(
        String oauthPermission,
        Nickname nickname,
        Email email,
        Avatar avatar,
        ProviderType providerType
    ) {
        this.oauthPermission = oauthPermission;
        this.nickname = nickname;
        this.email = email;
        this.avatar = avatar;
        this.providerType = providerType;
        this.role = Role.USER;
        this.status = Status.FREE;
    }

    public static MemberBuilder builder() {
        return new Member.MemberBuilder();
    }

    public void updateNickname(String nickname) {
        this.nickname = new Nickname(nickname);
    }

    public void updateAvatar(String profileImageUrl) {
        this.avatar = new Avatar(profileImageUrl);
    }

    public String getEmailValue() {
        return email.getEmail();
    }

    public String getNicknameValue() {
        return nickname.getNickname();
    }

    public String getAvatarValue() {
        return avatar.getAvatar();
    }

    public String getRoleValue() {
        return role.getRole();
    }

    public Email getEmail() {
        return email;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Role getRole() {
        return role;
    }

    public ProviderType getProviderType() {
        return providerType;
    }

    public Long getId() {
        return id;
    }

    public String getOauthPermission() {
        return oauthPermission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(email, member.email)
            && Objects.equals(nickname, member.nickname) && Objects.equals(avatar,
            member.avatar) && role == member.role && status == member.status
            && providerType == member.providerType && Objects.equals(oauthPermission,
            member.oauthPermission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, nickname, avatar, role, status, providerType,
            oauthPermission);
    }

    public static class MemberBuilder {

        private String oauthPermission;
        private Nickname nickname;
        private Email email;
        private Avatar avatar;
        private ProviderType providerType;

        private MemberBuilder() {
        }

        public MemberBuilder oauthPermission(final String oauthPermission) {
            this.oauthPermission = oauthPermission;
            return this;
        }

        public MemberBuilder nickname(final Nickname nickname) {
            this.nickname = nickname;
            return this;
        }

        public MemberBuilder email(final Email email) {
            this.email = email;
            return this;
        }

        public MemberBuilder avatar(final Avatar avatar) {
            this.avatar = avatar;
            return this;
        }

        public MemberBuilder providerType(ProviderType providerType) {
            this.providerType = providerType;
            return this;
        }

        public Member build() {
            return new Member(this.oauthPermission, this.nickname, this.email, this.avatar,
                this.providerType);
        }
    }
}