package com.example.msgadminapi.configuration.security.jwt;

import com.example.msgadminapi.configuration.utils.CookieUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final ObjectMapper objectMapper;
    private final CookieUtil cookieUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Cookie accessToken = cookieUtil.getCookie(request, "accessToken");
        if(accessToken != null && !tokenProvider.isTokenExpired(accessToken.getValue())) {
            try {
                String blackListexpiredAt = tokenProvider.redisGetValue(accessToken.getValue());
                if(blackListexpiredAt != null) {
                    throw new ExpiredJwtException(null, null, null);
                }
                String userId = accessTokenExtractEmail(accessToken.getValue());
                if(userId != null) registerUserinfoInSecurityContext(userId, request);
            } catch (MalformedJwtException e) {
                log.error("Invalid JWT token: {}", e.getMessage());
            } catch (ExpiredJwtException e) {
                log.error("JWT token is expired: {}", e.getMessage());
            } catch (UnsupportedJwtException e) {
                log.error("JWT token is unsupported: {}", e.getMessage());
            } catch (IllegalArgumentException e) {
                log.error("JWT claims string is empty: {}", e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }


    private void writeResponse(HttpServletResponse response, String accessToken) throws IOException {
        String bodyToJson = getBodyToJson();
        response.addHeader("accessToken", accessToken);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(bodyToJson);
    }

    private String getBodyToJson() throws JsonProcessingException {
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("msg", "Token is regenerated");
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        String bodyToJson = objectMapper.writeValueAsString(body);
        return bodyToJson;
    }

    private String accessTokenExtractEmail(String accessToken) {
        try {
            return tokenProvider.getUserEmail(accessToken);
        } catch (JwtException e) {
            throw new RuntimeException(e);
        }
    }

    private void registerUserinfoInSecurityContext(String userId, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken AuthenticationToken = tokenProvider.authentication(userId);
            AuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(AuthenticationToken);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNewAccessToken(String refreshToken) {
        try {
            return tokenProvider.generateAccessToken(tokenProvider.getUserEmail(refreshToken));
        } catch (JwtException | IllegalStateException e) {
            throw new RuntimeException();
        }
    }

}
