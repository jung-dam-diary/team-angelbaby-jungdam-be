package com.jungdam.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jungdam.common.domain.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MemberRefreshToken extends BaseEntity {

    @JsonIgnore
    @Id
    @Column(name = "member_refresh_token_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_oauth_permission", unique = true)
    private String oauthPermission;

    @Column(name = "member_refresh_token")
    private String refreshToken;

    protected MemberRefreshToken() {

    }

    public MemberRefreshToken(
        String oauthPermission,
        String refreshToken
    ) {
        this.oauthPermission = oauthPermission;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getId() {
        return id;
    }
}