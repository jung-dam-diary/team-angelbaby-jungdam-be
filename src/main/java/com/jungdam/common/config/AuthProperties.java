package com.jungdam.common.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth")
public class AuthProperties {

    private final OAuth oauth = new OAuth();
    private final OAuth2 oauth2 = new OAuth2();

    public AuthProperties() {
    }

    public OAuth getOauth() {
        return this.oauth;
    }

    public OAuth2 getOauth2() {
        return this.oauth2;
    }

    public static class OAuth {

        private String tokenSecret;
        private long tokenExpiry;
        private long refreshTokenExpiry;

        public OAuth() {
        }

        public OAuth(final String tokenSecret, final long tokenExpiry,
            final long refreshTokenExpiry) {
            this.tokenSecret = tokenSecret;
            this.tokenExpiry = tokenExpiry;
            this.refreshTokenExpiry = refreshTokenExpiry;
        }

        public String getTokenSecret() {
            return this.tokenSecret;
        }

        public void setTokenSecret(final String tokenSecret) {
            this.tokenSecret = tokenSecret;
        }

        public long getTokenExpiry() {
            return this.tokenExpiry;
        }

        public void setTokenExpiry(final long tokenExpiry) {
            this.tokenExpiry = tokenExpiry;
        }

        public long getRefreshTokenExpiry() {
            return this.refreshTokenExpiry;
        }

        public void setRefreshTokenExpiry(final long refreshTokenExpiry) {
            this.refreshTokenExpiry = refreshTokenExpiry;
        }
    }

    public static final class OAuth2 {

        private List<String> authorizedRedirectUris = new ArrayList();

        public OAuth2() {
        }

        public List<String> getAuthorizedRedirectUris() {
            return this.authorizedRedirectUris;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
}