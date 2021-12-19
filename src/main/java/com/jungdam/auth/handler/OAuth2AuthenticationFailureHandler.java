package com.jungdam.auth.handler;

import static com.jungdam.auth.infrastructure.OAuth2AuthorizationRequestBasedOnCookieRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

import com.jungdam.auth.infrastructure.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.jungdam.common.utils.CookieUtil;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final static String FAIL_TO_REDIRECT_URL = "/";
    private final static String QUERY_PARAM_FOR_ERROR = "error";

    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;

    public OAuth2AuthenticationFailureHandler(
        final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository) {
        this.authorizationRequestRepository = authorizationRequestRepository;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException {
        String targetUrl = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
            .map(Cookie::getValue)
            .orElse(FAIL_TO_REDIRECT_URL);

        exception.printStackTrace();

        targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam(QUERY_PARAM_FOR_ERROR, exception.getLocalizedMessage())
            .build()
            .toUriString();

        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}