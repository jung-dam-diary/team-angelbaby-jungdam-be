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
    @Column(name = "refresh_token_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_oauth_permission", unique = true)
    private String oauthPermission;

    @Column(name = "refresh_token")
    private String refreshToken;

    public MemberRefreshToken(
        String userId,
        String refreshToken
    ) {
        this.oauthPermission = userId;
        this.refreshToken = refreshToken;
    }

    protected MemberRefreshToken() {

    }

    public Long getId() {
        return id;
    }

    public String getOauthPermission() {
        return oauthPermission;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
