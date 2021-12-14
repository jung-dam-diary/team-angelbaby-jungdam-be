package com.jungdam.auth.presentation;

import com.jungdam.auth.token.AuthToken;
import com.jungdam.auth.token.AuthTokenProvider;
import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.properties.AuthProperties;
import com.jungdam.common.utils.CookieUtil;
import com.jungdam.common.utils.HeaderUtil;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.InvalidRefreshTokenException;
import com.jungdam.error.exception.NotExpiredException;
import com.jungdam.member.application.MemberRefreshTokenService;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import com.jungdam.member.domain.MemberRefreshToken;
import com.jungdam.member.domain.vo.Role;
import com.jungdam.member.infrastructure.MemberRefreshTokenRepository;
import com.jungdam.member.infrastructure.MemberRepository;
import io.jsonwebtoken.Claims;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO : 리펙토링 진행
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final static int REFRESH_TOKEN_EXPIRY_SECOND = 60;
    private final static long THREE_DAYS_MSEC = 259200000;
    private final static String REFRESH_TOKEN = "refresh_token";
    private final static String TOKEN_TITLE = "token";
    private final AuthProperties authProperties;
    private final AuthTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final MemberRefreshTokenRepository memberRefreshTokenRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final MemberRefreshTokenService memberRefreshTokenService;

    public AuthController(
        AuthProperties authProperties,
        AuthTokenProvider tokenProvider,
        AuthenticationManager authenticationManager,
        MemberRefreshTokenRepository memberRefreshTokenRepository,
        MemberRepository memberRepository,
        MemberService memberService,
        MemberRefreshTokenService memberRefreshTokenService) {
        this.authProperties = authProperties;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.memberRefreshTokenRepository = memberRefreshTokenRepository;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.memberRefreshTokenService = memberRefreshTokenService;
    }

    @GetMapping("/refresh")
    public ResponseEntity<ResponseDto<Map<String, String>>> refreshToken(HttpServletRequest request,
        HttpServletResponse response) {
        String accessToken = HeaderUtil.getAccessToken(request);
        AuthToken authToken = tokenProvider.convertAuthToken(accessToken);
        if (!authToken.validate()) {
            throw new NotExpiredException(ErrorMessage.NOT_EXPIRED_TOKEN_YET);
        }

        Claims claims = makeClaims(authToken);

        Long memberId = Long.parseLong(claims.getSubject());

        Role roleType = Role.of(claims.get("role", String.class));

        String refreshToken = CookieUtil.getCookie(request, REFRESH_TOKEN)
            .map(Cookie::getValue)
            .orElse((null));
        AuthToken authRefreshToken = tokenProvider.convertAuthToken(refreshToken);

        if (authRefreshToken.validate()) {
            throw new InvalidRefreshTokenException(ErrorMessage.INVALID_REFRESH_TOKEN);
        }

        Member member = memberService.findById(memberId);

        MemberRefreshToken userRefreshToken = memberRefreshTokenService.findByOauthPermissionAndRefreshToken(
            member.getOauthPermission(), refreshToken);

        if (Objects.isNull(userRefreshToken)) {
            throw new InvalidRefreshTokenException(ErrorMessage.INVALID_REFRESH_TOKEN);
        }

        Date now = new Date();

        long validTime =
            authRefreshToken.getTokenClaims().getExpiration().getTime() - now.getTime();

        if (validTime <= THREE_DAYS_MSEC) {
            long refreshTokenExpiry = authProperties.getOauth().getRefreshTokenExpiry();

            authRefreshToken = tokenProvider.createAuthToken(
                authProperties.getOauth().getTokenSecret(),
                new Date(now.getTime() + refreshTokenExpiry)
            );

            memberRefreshTokenService.updateRefreshToken(authRefreshToken.getToken(),
                member.getOauthPermission()
            );

            int cookieMaxAge = (int) refreshTokenExpiry / REFRESH_TOKEN_EXPIRY_SECOND;

            updateCookie(request, response, authRefreshToken, cookieMaxAge);
        }

        return ResponseDto.of(ResponseMessage.TOKEN_REFRESH_SUCCESS,
            makeToken(memberId, roleType, now));
    }

    private Claims makeClaims(AuthToken authToken) {
        Claims claims = authToken.getExpiredTokenClaims();
        if (Objects.isNull(claims)) {
            throw new NotExpiredException(ErrorMessage.NOT_EXPIRED_TOKEN_YET);
        }
        return claims;
    }

    private Map<String, String> makeToken(Long memberId, Role roleType, Date now) {
        AuthToken newAccessToken = tokenProvider.createAuthToken(
            String.valueOf(memberId),
            roleType.getRole(),
            new Date(now.getTime() + authProperties.getOauth().getTokenExpiry())
        );

        Map<String, String> token = new HashMap<>();
        token.put(TOKEN_TITLE, newAccessToken.getToken());
        return token;
    }

    private void updateCookie(HttpServletRequest request, HttpServletResponse response,
        AuthToken authRefreshToken, int cookieMaxAge) {
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
        CookieUtil.addCookie(response, REFRESH_TOKEN, authRefreshToken.getToken(),
            cookieMaxAge);
    }
}