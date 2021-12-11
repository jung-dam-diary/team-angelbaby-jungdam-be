package com.jungdam.member.application;

import com.jungdam.member.domain.MemberRefreshToken;
import com.jungdam.member.infrastructure.MemberRefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberRefreshTokenService {

    private final MemberRefreshTokenRepository memberRefreshTokenRepository;

    public MemberRefreshTokenService(
        MemberRefreshTokenRepository memberRefreshTokenRepository) {
        this.memberRefreshTokenRepository = memberRefreshTokenRepository;
    }

    @Transactional(readOnly = true)
    public MemberRefreshToken findByOauthPermissionAndRefreshToken(String oauthPermission,
        String token) {
        return memberRefreshTokenRepository.findByOauthPermissionAndRefreshToken(
            oauthPermission, token
        );
    }

    @Transactional
    public void updateRefreshToken(String token, String oauthPermission) {
        final MemberRefreshToken byOauthPermission = memberRefreshTokenRepository.findByOauthPermission(
            oauthPermission);
        byOauthPermission.updateRefreshToken(token);
    }
}