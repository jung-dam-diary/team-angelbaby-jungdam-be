package com.jungdam.auth.handler;

import static com.jungdam.auth.infrastructure.OAuth2AuthorizationRequestBasedOnCookieRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
import static com.jungdam.auth.infrastructure.OAuth2AuthorizationRequestBasedOnCookieRepository.REFRESH_TOKEN;

import com.jungdam.auth.infrastructure.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.jungdam.auth.oauth2.OAuth2MemberInfo;
import com.jungdam.auth.oauth2.OAuth2MemberInfoFactory;
import com.jungdam.auth.token.AuthToken;
import com.jungdam.auth.token.AuthTokenProvider;
import com.jungdam.common.properties.AuthProperties;
import com.jungdam.common.utils.CookieUtils;
import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.auth.FailAuthenticationException;
import com.jungdam.member.domain.Member;
import com.jungdam.member.domain.MemberRefreshToken;
import com.jungdam.member.domain.vo.ProviderType;
import com.jungdam.member.domain.vo.Role;
import com.jungdam.member.infrastructure.MemberRefreshTokenRepository;
import com.jungdam.member.infrastructure.MemberRepository;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final static String QUERY_PARAM_FOR_TOKEN = "token";
    private final static int REFRESH_TOKEN_EXPIRY_SECONDS = 60;

    private final AuthTokenProvider tokenProvider;
    private final AuthProperties authProperties;
    private final MemberRefreshTokenRepository memberRefreshTokenRepository;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;
    private final MemberRepository memberRepository;

    public OAuth2AuthenticationSuccessHandler(final AuthTokenProvider tokenProvider,
        final AuthProperties authProperties,
        final MemberRefreshTokenRepository memberRefreshTokenRepository,
        final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository,
        final MemberRepository memberRepository) {
        this.tokenProvider = tokenProvider;
        this.authProperties = authProperties;
        this.memberRefreshTokenRepository = memberRefreshTokenRepository;
        this.authorizationRequestRepository = authorizationRequestRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) {
        OAuth2MemberInfo oAuth2MemberInfo = bringOAuth2MemberInfo(authentication);
        Date now = new Date();
        refreshToken(request, response, oAuth2MemberInfo, now);
        return makeURI(request, authentication, oAuth2MemberInfo, now);
    }

    private String makeURI(HttpServletRequest request, Authentication authentication,
        OAuth2MemberInfo oAuth2MemberInfo, Date now) {
        String targetUrl = bringTargetUrl(request);
        Role role = bringRole(authentication);
        AuthToken accessToken = makeAccessToken(oAuth2MemberInfo, role, now);
        return buildURI(targetUrl, accessToken);
    }

    private void refreshToken(HttpServletRequest request, HttpServletResponse response,
        OAuth2MemberInfo oAuth2MemberInfo, Date now) {
        long refreshTokenExpiry = getRefreshTokenExpiry();
        AuthToken refreshToken = makeRefreshToken(now, refreshTokenExpiry);
        updateToken(oAuth2MemberInfo, refreshToken);
        updateCookieInfo(request, response, (int) refreshTokenExpiry, refreshToken);
    }

    private void updateToken(OAuth2MemberInfo oAuth2MemberInfo, AuthToken refreshToken) {
        MemberRefreshToken memberRefreshToken = findByOauthPermission(oAuth2MemberInfo);
        updateOrCreateRefreshToken(oAuth2MemberInfo, refreshToken, memberRefreshToken);
    }

    private long getRefreshTokenExpiry() {
        return authProperties.getOauth().getRefreshTokenExpiry();
    }

    private String buildURI(String targetUrl, AuthToken accessToken) {
        return UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam(
                QUERY_PARAM_FOR_TOKEN,
                accessToken.getToken()
            )
            .build()
            .toUriString();
    }

    private void updateCookieInfo(HttpServletRequest request, HttpServletResponse response,
        int refreshTokenExpiry, AuthToken refreshToken) {
        int cookieMaxAge = calculateRefreshTokenExpiry(refreshTokenExpiry);
        updateCookie(request, response, refreshToken, cookieMaxAge);
    }

    private MemberRefreshToken findByOauthPermission(OAuth2MemberInfo oAuth2MemberInfo) {
        return memberRefreshTokenRepository.findByOauthPermission(
            oAuth2MemberInfo.getOauthPermission());
    }

    private void updateOrCreateRefreshToken(OAuth2MemberInfo oAuth2MemberInfo,
        AuthToken refreshToken,
        MemberRefreshToken memberRefreshToken) {
        if (!Objects.isNull(memberRefreshToken)) {
            memberRefreshToken.updateRefreshToken(refreshToken.getToken());
            return;
        }
        memberRefreshToken = new MemberRefreshToken(
            oAuth2MemberInfo.getOauthPermission(),
            refreshToken.getToken()
        );
        memberRefreshTokenRepository.saveAndFlush(memberRefreshToken);
    }

    private AuthToken makeRefreshToken(Date now, long refreshTokenExpiry) {
        return tokenProvider.createAuthToken(
            authProperties.getOauth().getTokenSecret(),
            new Date(now.getTime() + refreshTokenExpiry)
        );
    }

    private AuthToken makeAccessToken(OAuth2MemberInfo oAuth2MemberInfo, Role role, Date now) {
        Member member = memberRepository.findByOauthPermission(
            oAuth2MemberInfo.getOauthPermission());

        return tokenProvider.createAuthToken(
            String.valueOf(member.getId()),
            role.getRole(),
            new Date(now.getTime() + authProperties.getOauth().getTokenExpiry())
        );
    }

    private Role bringRole(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = ((OidcUser) authentication.getPrincipal()).getAuthorities();

        if (hasAuthority(authorities, Role.ADMIN.getRole())) {
            return Role.ADMIN;
        }
        return Role.USER;
    }

    private OAuth2MemberInfo bringOAuth2MemberInfo(Authentication authentication) {
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        ProviderType providerType = bringProviderType(authToken);

        OidcUser oidcUser = ((OidcUser) authentication.getPrincipal());

        return OAuth2MemberInfoFactory.of(
            providerType,
            oidcUser.getAttributes()
        );
    }

    private String bringTargetUrl(HttpServletRequest request) {
        Optional<String> redirectUri = CookieUtils.getCookie(request,
                REDIRECT_URI_PARAM_COOKIE_NAME)
            .map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new FailAuthenticationException(ErrorMessage.UNAUTHORIZED_REDIRECT_URI);
        }

        return redirectUri.orElse(getDefaultTargetUrl());
    }

    private ProviderType bringProviderType(OAuth2AuthenticationToken authToken) {
        String provider = authToken.getAuthorizedClientRegistrationId().toUpperCase();
        return ProviderType.of(provider);
    }

    private int calculateRefreshTokenExpiry(int refreshTokenExpiry) {
        return refreshTokenExpiry / REFRESH_TOKEN_EXPIRY_SECONDS;
    }

    private void updateCookie(HttpServletRequest request, HttpServletResponse response,
        AuthToken refreshToken, int cookieMaxAge) {
        CookieUtils.deleteCookie(request, response, REFRESH_TOKEN);
        CookieUtils.addCookie(response, REFRESH_TOKEN, refreshToken.getToken(), cookieMaxAge);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request,
        HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean hasAuthority(Collection<? extends GrantedAuthority> authorities,
        String authority) {
        if (Objects.isNull(authorities)) {
            return false;
        }

        return authorities.stream()
            .anyMatch(g -> Objects.equals(authority, g.getAuthority()));
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);

        return authorizedRedirectUris()
            .stream()
            .anyMatch(authorizedRedirectUri -> {
                URI authorizedUri = URI.create(authorizedRedirectUri);

                return isEqualsUrl(clientRedirectUri, authorizedUri)
                    && isEqualsPort(clientRedirectUri, authorizedUri);
            });
    }

    private List<String> authorizedRedirectUris() {
        return authProperties.getOauth2()
            .getAuthorizedRedirectUris();
    }

    private boolean isEqualsUrl(URI clientRedirectUri, URI authorizedURI) {
        return authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost());
    }

    private boolean isEqualsPort(URI clientRedirectUri, URI authorizedURI) {
        return authorizedURI.getPort() == clientRedirectUri.getPort();
    }
}