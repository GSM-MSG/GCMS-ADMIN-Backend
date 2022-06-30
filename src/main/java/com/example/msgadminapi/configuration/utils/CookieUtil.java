package com.example.msgadminapi.configuration.utils;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class CookieUtil {
    public Cookie createCookie(String name, String value, int exp) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(exp / 1000);
        cookie.setSecure(true);
        cookie.setDomain("gcms.site");
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if(cookies == null) return null;
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(name))
                return cookie;
        }
        return null;
    }

}
