package com.jungdam.common.utils;

import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

public class CookieUtils {

    private final static int COOKIE_MIN_LENGTH = 0;
    private final static int COOKIE_MAX_AGE = 0;
    private final static String COOKIE_VALUE = "";
    private final static String COOKIE_PATH = "/";

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (!Objects.isNull(cookies) && cookies.length > COOKIE_MIN_LENGTH) {
            for (Cookie cookie : cookies) {
                if (Objects.equals(name, cookie.getName())) {
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
            Arrays.stream(cookies).filter(cookie -> name.equals(cookie.getName()))
                .forEach(cookie -> {
                    cookie.setValue(COOKIE_VALUE);
                    cookie.setPath(COOKIE_PATH);
                    cookie.setMaxAge(COOKIE_MAX_AGE);
                    response.addCookie(cookie);
                });
        }
    }

    public static String serialized(Object object) {
        return Base64.getUrlEncoder()
            .encodeToString(serialize(object));
    }

    private static byte[] serialize(Object object) {
        return SerializationUtils.serialize(object);
    }

    public static <T> T deserialized(Cookie cookie, Class<T> cls) {
        return cls.cast(deserialize(cookie));
    }

    private static Object deserialize(Cookie cookie) {
        return SerializationUtils.deserialize(decode(cookie));
    }

    private static byte[] decode(Cookie cookie) {
        return Base64.getUrlDecoder().decode(cookie.getValue());
    }
}