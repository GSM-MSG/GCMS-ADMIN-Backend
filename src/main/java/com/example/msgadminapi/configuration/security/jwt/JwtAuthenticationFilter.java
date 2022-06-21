package com.example.msgadminapi.configuration.security.jwt;

import com.example.msgadminapi.configuration.security.auth.MyUserDetailService;
import com.example.msgadminapi.domain.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final ObjectMapper objectMapper;
    private final MyUserDetailService myUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String accessToken = request.getHeader("Authorization");
        String refreshToken = request.getHeader("RefreshToken");
        if(accessToken != null && refreshToken != null && tokenProvider.getTokenType(accessToken).equals("accessToken")) {
            if(tokenProvider.isTokenExpired(accessToken) && tokenProvider.getTokenType(refreshToken).equals("refreshToken") && !tokenProvider.isTokenExpired(refreshToken)) {
                System.out.println("hi1");
                accessToken = generateNewAccessToken(refreshToken);
                writeResponse(response, accessToken);
                return;
            }
            String userEmail = accessTokenExtractEmail(accessToken);
            if(userEmail != null) registerUserinfoInSecurityContext(userEmail, request);
        }
        filterChain.doFilter(request, response);
    }
