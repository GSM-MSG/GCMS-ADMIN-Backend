package com.example.msgadminapi.configuration.security.jwt;

import com.example.msgadminapi.configuration.security.auth.MyUserDetailService;
import com.example.msgadminapi.exception.exception.RefreshTokenExpiredException;
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
import java.time.Duration;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class TokenProvider {
    private final MyUserDetailService myUserDetailService;
    private final RedisService redisService;

    public static final int ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 6; // 6시간
    public static final int REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14; // 14일

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.blacklist.access-token}")
    private String blackListATPrefix;

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
    private String doGenerateToken(String userId, TokenType tokenType, long expireTime) {
        final Claims claims = Jwts.claims().setSubject(userId);
        claims.put("tokenType", tokenType.value);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateAccessToken(String userId) {
        return doGenerateToken(userId, TokenType.ACCESS_TOKEN, ACCESS_TOKEN_EXPIRE_TIME);
    }
    public String generateRefreshToken(String userId) {
        String refreshToken = doGenerateToken(userId, TokenType.REFRESH_TOKEN, REFRESH_TOKEN_EXPIRE_TIME);
        redisService.setValues(userId, refreshToken);
        return refreshToken;
    }

    public UsernamePasswordAuthenticationToken authentication(String userId) {
        UserDetails userDetails = myUserDetailService.loadUserByUsername(userId);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public void checkRefreshToken(String userId, String refreshToken) {
            String redisRT = redisService.getValues(userId);

            if(!refreshToken.equals(redisRT)) {
                throw new RefreshTokenExpiredException();
            }
    }

    public void logout(String userId, String accessToken) {
        long expiredAccessTokenTime = getExpiredTime(accessToken).getTime() - new Date().getTime();
        redisService.setValues(blackListATPrefix + accessToken, userId, Duration.ofMillis(expiredAccessTokenTime));
        redisService.deleteValues(userId);
    }

    private Date getExpiredTime(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token).getBody().getExpiration();
    }

    public String redisGetValue(String token) {
        return redisService.getValues(blackListATPrefix + token);
    }
}

