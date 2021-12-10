package com.jungdam.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    private String allowedOrigins;
    private String allowedMethods;
    private String allowedHeaders;
    private Long maxAge;

    public CorsProperties() {
    }

    public String getAllowedOrigins() {
        return this.allowedOrigins;
    }

    public void setAllowedOrigins(final String allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public String getAllowedMethods() {
        return this.allowedMethods;
    }

    public void setAllowedMethods(final String allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public String getAllowedHeaders() {
        return this.allowedHeaders;
    }

    public void setAllowedHeaders(final String allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    public Long getMaxAge() {
        return this.maxAge;
    }

    public void setMaxAge(final Long maxAge) {
        this.maxAge = maxAge;
    }
}