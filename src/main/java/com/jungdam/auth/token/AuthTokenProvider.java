package com.jungdam.auth.token;

import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.token.TokenValidFailedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthTokenProvider {

    private static final String AUTHORITIES_KEY = "role";
    private static final String USER_PASSWORD = "NO_PASSWORD";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final Key key;

    public AuthTokenProvider(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public AuthToken createAuthToken(String email, Date expiry) {
        return new AuthToken(email, expiry, key);
    }

    public AuthToken createAuthToken(String email, String role, Date expiry) {
        return new AuthToken(email, role, expiry, key);
    }

    public AuthToken convertAuthToken(String token) {
        return new AuthToken(token, key);
    }

    public Authentication getAuthentication(AuthToken authToken) {
        if (authToken.validate()) {
            Claims claims = authToken.getTokenClaims();
            Collection<? extends GrantedAuthority> authorities = getGrantedAuthorities(
                claims);

            log.debug("claims subject := [{}]", claims.getSubject());
            User principal = new User(claims.getSubject(), USER_PASSWORD, authorities);

            return new UsernamePasswordAuthenticationToken(principal, authToken, authorities);
        }
        throw new TokenValidFailedException(ErrorMessage.FAIL_TO_GENERATE_TOKEN);
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(Claims claims) {
        return Arrays.stream(convertClaimToAuthorities(claims))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    private String[] convertClaimToAuthorities(Claims claims) {
        return new String[]{claims.get(AUTHORITIES_KEY).toString()};
    }
}