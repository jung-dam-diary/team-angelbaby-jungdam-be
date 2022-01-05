package com.jungdam.common.utils;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.NotExistException;
import java.util.Objects;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static Long getCurrentUsername() {
        Authentication authentication = getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            return Long.parseLong(springSecurityUser.getUsername());
        }
        return (Long) authentication.getPrincipal();
    }

    private static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        isNullAuthentication(authentication);
        return authentication;
    }

    private static void isNullAuthentication(Authentication authentication) {
        if (Objects.isNull(authentication)) {
            throw new NotExistException(ErrorMessage.NOT_EXIST_MEMBER);
        }
    }
}