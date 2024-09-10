package com.plj.hub.gateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private final SecretKey key;
    private final String issuer;

    public JwtUtils(@Value("${jwt.secret}") String secret, @Value("${jwt.issuer}")String issuer) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.issuer = issuer;
    }

    // JWT 토큰에서 사용자 아이디 추출
    public String extractUserId(String token) {
        return extractAllClaims(token).getId();
    }

    // JWT 토큰에서 발행 기관 추출
    public String extractIssuer(String token) {
        return extractAllClaims(token).getIssuer();
    }

    // JWT 토큰에서 사용하 권한 추출
    public String extractUserRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // 토큰이 만료되었는지 확인
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // 토큰의 유효성 검증
    public boolean validateToken(String token) {
        String extractedUserId = extractUserId(token);
        String extractedIssuer = extractIssuer(token);
        return (extractedUserId != null && issuer.equals(extractedIssuer) && !isTokenExpired(token));
    }

    // 토큰에서 모든 클레임을 추출
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)  // 서명을 검증할 비밀키 설정
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
