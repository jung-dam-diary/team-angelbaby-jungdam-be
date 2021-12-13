package com.jungdam.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jungdam.common.domain.BaseEntity;
import com.jungdam.member.domain.vo.Avatar;
import com.jungdam.member.domain.vo.Email;
import com.jungdam.member.domain.vo.Nickname;
import com.jungdam.member.domain.vo.ProviderType;
import com.jungdam.member.domain.vo.Role;
import com.jungdam.member.domain.vo.Status;
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

    @JsonIgnore
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
        String nickname,
        String email,
        String avatar,
        ProviderType providerType
    ) {
        this.oauthPermission = oauthPermission;
        this.nickname = new Nickname(nickname);
        this.email = new Email(email);
        this.avatar = new Avatar(avatar);
        this.providerType = providerType;
        this.role = Role.USER;
        this.status = Status.FREE;
    }

    public static Member.MemberBuilder builder() {
        return new Member.MemberBuilder();
    }

    public void updateNickname(String nickname) {
        this.nickname = new Nickname(nickname);
    }

    public void updateAvatar(String profileImageUrl) {
        this.avatar = new Avatar(profileImageUrl);
    }

    public String getNickname() {
        return nickname.getNickname();
    }

    public String getAvatar() {
        return avatar.getAvatar();
    }

    public ProviderType getProviderType() {
        return providerType;
    }

    public String getRole() {
        return role.getRole();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email.getEmail();
    }

    public String getOauthPermission() {
        return oauthPermission;
    }

    public static class MemberBuilder {

        private String oauthPermission;
        private String nickname;
        private String email;
        private String avatar;
        private ProviderType providerType;

        private MemberBuilder() {
        }

        public Member.MemberBuilder oauthPermission(final String oauthPermission) {
            this.oauthPermission = oauthPermission;
            return this;
        }

        public Member.MemberBuilder nickname(final String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Member.MemberBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public Member.MemberBuilder avatar(final String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Member.MemberBuilder providerType(ProviderType providerType) {
            this.providerType = providerType;
            return this;
        }

        public Member build() {
            return new Member(this.oauthPermission, this.nickname, this.email, this.avatar,
                this.providerType);
        }
    }
}