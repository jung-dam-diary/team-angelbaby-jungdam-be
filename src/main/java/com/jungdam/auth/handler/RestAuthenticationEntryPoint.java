package com.jungdam.auth.handler;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException
    ) throws IOException {
        authException.printStackTrace();
        log.info("Responding with unauthorized error. Message := {}", authException.getMessage());
        response.sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            authException.getLocalizedMessage()
        );
    }
}