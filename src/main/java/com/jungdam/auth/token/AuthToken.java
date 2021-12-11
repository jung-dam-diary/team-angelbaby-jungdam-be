package com.jungdam.auth.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.security.Key;
import java.util.Date;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthToken {

    private static final String AUTHORITIES_KEY = "role";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final String token;
    private final Key key;

    public AuthToken(String token, Key key) {
        this.token = token;
        this.key = key;
    }

    AuthToken(String id, Date expiry, Key key) {
        this.key = key;
        this.token = createAuthToken(id, expiry);
    }

    AuthToken(String id, String role, Date expiry, Key key) {
        this.key = key;
        this.token = createAuthToken(id, role, expiry);
    }

    public String getToken() {
        return token;
    }

    private String createAuthToken(String id, Date expiry) {
        return Jwts.builder()
            .setSubject(id)
            .signWith(key, SignatureAlgorithm.HS256)
            .setExpiration(expiry)
            .compact();
    }

    private String createAuthToken(String id, String role, Date expiry) {
        return Jwts.builder()
            .setSubject(id)
            .claim(AUTHORITIES_KEY, role)
            .signWith(key, SignatureAlgorithm.HS256)
            .setExpiration(expiry)
            .compact();
    }

    public boolean validate() {
        return !Objects.isNull(this.getTokenClaims());
    }

    public Claims getTokenClaims() {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (SecurityException exception) {
            log.info("Invalid JWT signature.");
        } catch (MalformedJwtException exception) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException exception) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException exception) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException exception) {
            log.info("JWT token compact of handler are invalid.");
        }
        return null;
    }

    public Claims getExpiredTokenClaims() {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException exception) {
            log.info("Expired JWT token.");
            return exception.getClaims();
        }
        return null;
    }
}