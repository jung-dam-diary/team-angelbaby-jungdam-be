package com.jungdam.auth.handler;

import static com.jungdam.auth.infrastructure.OAuth2AuthorizationRequestBasedOnCookieRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
import static com.jungdam.auth.infrastructure.OAuth2AuthorizationRequestBasedOnCookieRepository.REFRESH_TOKEN;

import com.jungdam.auth.infrastructure.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.jungdam.auth.oauth2.OAuth2MemberInfo;
import com.jungdam.auth.oauth2.OAuth2MemberInfoFactory;
import com.jungdam.auth.token.AuthToken;
import com.jungdam.auth.token.AuthTokenProvider;
import com.jungdam.common.properties.AuthProperties;
import com.jungdam.common.utils.CookieUtil;
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

// TODO : 리펙토링 진행
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

        Optional<String> redirectUri = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
            .map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new IllegalArgumentException(
                "Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        ProviderType providerType = ProviderType.valueOf(
            authToken.getAuthorizedClientRegistrationId().toUpperCase()
        );

        OidcUser oidcUser = ((OidcUser) authentication.getPrincipal());
        OAuth2MemberInfo oAuth2MemberInfo = OAuth2MemberInfoFactory.of(
            providerType,
            oidcUser.getAttributes());
        Collection<? extends GrantedAuthority> authorities = ((OidcUser) authentication.getPrincipal()).getAuthorities();

        Role role = hasAuthority(authorities, Role.ADMIN.getRole()) ? Role.ADMIN : Role.USER;

        Date now = new Date();

        Member member = memberRepository.findByOauthPermission(
            oAuth2MemberInfo.getOauthPermission());

        AuthToken accessToken = tokenProvider.createAuthToken(
            String.valueOf(member.getId()),
            role.getRole(),
            new Date(now.getTime() + authProperties.getOauth().getTokenExpiry())
        );

        long refreshTokenExpiry = authProperties.getOauth().getRefreshTokenExpiry();

        AuthToken refreshToken = tokenProvider.createAuthToken(
            authProperties.getOauth().getTokenSecret(),
            new Date(now.getTime() + refreshTokenExpiry)
        );

        MemberRefreshToken memberRefreshToken = memberRefreshTokenRepository.findByOauthPermission(
            oAuth2MemberInfo.getOauthPermission());
        if (!Objects.isNull(memberRefreshToken)) {
            memberRefreshToken.updateRefreshToken(refreshToken.getToken());
        } else {
            memberRefreshToken = new MemberRefreshToken(
                oAuth2MemberInfo.getOauthPermission(),
                refreshToken.getToken()
            );
            memberRefreshTokenRepository.saveAndFlush(memberRefreshToken);
        }

        int cookieMaxAge = calculateRefreshTokenExpiry((int) refreshTokenExpiry);

        updateCookie(request, response, refreshToken, cookieMaxAge);

        return UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam(
                QUERY_PARAM_FOR_TOKEN,
                accessToken.getToken()
            )
            .build()
            .toUriString();
    }

    private int calculateRefreshTokenExpiry(int refreshTokenExpiry) {
        return refreshTokenExpiry / REFRESH_TOKEN_EXPIRY_SECONDS;
    }

    private void updateCookie(HttpServletRequest request, HttpServletResponse response,
        AuthToken refreshToken, int cookieMaxAge) {
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
        CookieUtil.addCookie(response, REFRESH_TOKEN, refreshToken.getToken(), cookieMaxAge);
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

        for (GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);

        return authProperties.getOauth2().getAuthorizedRedirectUris()
            .stream()
            .anyMatch(authorizedRedirectUri -> {
                URI authorizedURI = URI.create(authorizedRedirectUri);
                if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                    && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                    return true;
                }
                return false;
            });
    }
}
