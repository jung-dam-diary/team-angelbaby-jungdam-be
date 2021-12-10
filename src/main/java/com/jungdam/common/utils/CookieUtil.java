package com.jungdam.common.utils;

import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

public class CookieUtil {

    private final static int COOKIE_MIN_LENGTH = 0;
    private final static int COOKIE_MAX_AGE = 0;
    private final static String COOKIE_VALUE = "";
    private final static String COOKIE_PATH = "/";

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (!Objects.isNull(cookies) && cookies.length > COOKIE_MIN_LENGTH) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

    public static void addCookie(HttpServletResponse response, String name, String value,
        int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(COOKIE_PATH);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);

        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
        String name) {
        Cookie[] cookies = request.getCookies();

        if (!Objects.isNull(cookies) && cookies.length > COOKIE_MIN_LENGTH) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setValue(COOKIE_VALUE);
                    cookie.setPath(COOKIE_PATH);
                    cookie.setMaxAge(COOKIE_MAX_AGE);
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
            .encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
            SerializationUtils.deserialize(
                Base64.getUrlDecoder().decode(cookie.getValue())
            )
        );
    }
}