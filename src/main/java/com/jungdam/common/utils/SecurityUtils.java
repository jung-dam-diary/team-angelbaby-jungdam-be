package com.jungdam.common.utils;

import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.NotExistException;
import java.util.Objects;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        isNullAuthentication(authentication);

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            return springSecurityUser.getUsername();
        }
        return (String) authentication.getPrincipal();
    }

    private static void isNullAuthentication(Authentication authentication) {
        if (Objects.isNull(authentication)) {
            throw new NotExistException(ErrorMessage.NOT_EXIST_MEMBER);
        }
    }
}