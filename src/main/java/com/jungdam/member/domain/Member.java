package com.jungdam.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jungdam.common.domain.BaseEntity;
import com.jungdam.member.domain.vo.Avatar;
import com.jungdam.member.domain.vo.Email;
import com.jungdam.member.domain.vo.Nickname;
import com.jungdam.member.domain.vo.Password;
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

    @Embedded
    private Password password;

    @Column(name = "member_provider_type")
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(name = "member_oauth_permission", unique = true)
    private String oauthPermission;

    protected Member() {

    }

    public Member(
        String userId,
        String username,
        String email,
        String avatar,
        ProviderType providerType
    ) {
        this.oauthPermission = userId;
        this.nickname = new Nickname(username);
        this.password = new Password("NO_PASS");
        this.email = Objects.isNull(email) ? new Email("NO_EMAIL") : new Email(email);
        this.avatar = Objects.isNull(avatar) ? new Avatar("NO_AVATAR") : new Avatar(avatar);
        this.providerType = providerType;
        this.role = Role.USER;
        this.status = Status.FREE;
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


    public String getPassword() {
        return password.getPassword();
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
}