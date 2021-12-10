package com.jungdam.auth.domain;

import com.jungdam.member.domain.Member;
import com.jungdam.member.domain.vo.ProviderType;
import com.jungdam.member.domain.vo.Role;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class AuthPrincipal implements OAuth2User, UserDetails, OidcUser {

    private final Long id;
    private final String email;
    private final String oauthPermission;
    private final String password;
    private final ProviderType providerType;
    private final Role role;
    private final Collection<GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public AuthPrincipal(final Long id, final String email, final String oauthPermission,
        final String password,
        final ProviderType providerType, final Role role,
        final Collection<GrantedAuthority> authorities, final Map<String, Object> attributes) {
        this.id = id;
        this.email = email;
        this.oauthPermission = oauthPermission;
        this.password = password;
        this.providerType = providerType;
        this.role = role;
        this.authorities = authorities;
        this.attributes = attributes;
    }

    public AuthPrincipal(final Long id, final String email, final String oauthPermission,
        final String password,
        final ProviderType providerType, final Role role,
        final Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.oauthPermission = oauthPermission;
        this.password = password;
        this.providerType = providerType;
        this.role = role;
        this.authorities = authorities;
    }

    public static AuthPrincipal create(Member member) {
        return new AuthPrincipal(
            member.getId(),
            member.getEmail(),
            member.getOauthPermission(),
            member.getPassword(),
            member.getProviderType(),
            Role.USER,
            Collections.singletonList(new SimpleGrantedAuthority(Role.USER.getRole()))
        );
    }

    public static AuthPrincipal create(Member member, Map<String, Object> attributes) {
        AuthPrincipal authPrincipal = create(member);
        authPrincipal.initAttributes(attributes);

        return authPrincipal;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return oauthPermission;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getClaims() {
        return null;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }

    public void initAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }
}
