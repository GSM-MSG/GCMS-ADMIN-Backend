package com.example.msgadminapi.configuration.utils;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class CookieUtil {
    public Cookie createCookie(String name, String value, int exp) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(exp / 1000); //(int) (System.currentTimeMillis() + exp / 1000)
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setDomain("gcms.site");
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();

        if(cookies == null) return null;
        for(Cookie cookie : cookies) {
            System.out.println("filter cookies " + cookie.getName());
            System.out.println("filter cookies value " + cookie.getValue());
            if(cookie.getName().equals(name))
                return cookie;
        }
        return null;
    }

}
