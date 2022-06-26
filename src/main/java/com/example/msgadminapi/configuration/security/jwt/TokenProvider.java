package com.example.msgadminapi.configuration.security.jwt;

import com.example.msgadminapi.configuration.security.auth.MyUserDetailService;
import com.example.msgadminapi.service.RedisService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class TokenProvider {
    private final MyUserDetailService myUserDetailService;
    private final RedisService redisService;

    public static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 3; // 3시간
    public static final long REFRESH_TOKEN_EXPIRE_TIME = ACCESS_TOKEN_EXPIRE_TIME * 8 * 180;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    enum TokenType {
        ACCESS_TOKEN("accessToken"),
        REFRESH_TOKEN("refreshToken");
        String value;
        TokenType(String value) {
            this.value = value;
        }
    }
    enum TokenClaimName {
        USER_EMAIL("userEmail"),
        TOKEN_TYPE("tokenType");
        String value;
        TokenClaimName(String value) {
            this.value = value;
        }
    }
    private Key getSigningKey(String secretKey) {
        byte keyByte[] = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyByte);
    }
    public Claims extractAllClaims(String token) throws ExpiredJwtException, IllegalStateException, MalformedJwtException, SignatureException, UnsupportedJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserEmail(String token) {
        return extractAllClaims(token).getSubject();
    }
    public String getTokenType(String token) {
        return extractAllClaims(token).get(TokenClaimName.TOKEN_TYPE.value, String.class);
    }
    public Boolean isTokenExpired(String token) {
        try {
            extractAllClaims(token).getExpiration();
            return false;
        }
        catch (ExpiredJwtException e) {
            return true;
        }
    }
    private String doGenerateToken(String userEmail, TokenType tokenType, long expireTime) {
        final Claims claims = Jwts.claims().setSubject(userEmail);
        claims.put("tokenType", tokenType.value);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateAccessToken(String email) {
        return doGenerateToken(email, TokenType.ACCESS_TOKEN, ACCESS_TOKEN_EXPIRE_TIME);
    }
    public String generateRefreshToken(String email) {
        String refreshToken = doGenerateToken(email, TokenType.REFRESH_TOKEN, REFRESH_TOKEN_EXPIRE_TIME);
        redisService.setValues(email, refreshToken);
        return refreshToken;
    }

    public UsernamePasswordAuthenticationToken authentication(String userEmail) {
        UserDetails userDetails = myUserDetailService.loadUserByUsername(userEmail);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public void checkRefreshToken(String email, String refreshToken) {
        String redisRT = redisService.getValues(email);
        if(!refreshToken.equals(redisRT)) {
            throw new RuntimeException();
        }
    }
}
