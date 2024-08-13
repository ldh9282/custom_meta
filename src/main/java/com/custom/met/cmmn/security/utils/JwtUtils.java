package com.custom.met.cmmn.security.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * <pre>
 * 클래스명: JwtUtils
 * 설명: JWT 유틸
 * </pre>
 */
@Component
public class JwtUtils {
	
	@Value("${jwt-secret-key}")
	private String jwtSecretKey;
	
	@Value("${jwt-access-expiration-ms}")
	private long jwtAccessExpirationMs;
	
	@Value("${jwt-refresh-expiration-ms}")
	private long jwtRefreshExpirationMs;
	
	/**
     * <pre>
     * 메소드명: generateRefreshToken
     * 설명: 로그인 아이디로 엑세스 토큰 생성
     * </pre>
     * @param username
     * @return
     */
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtAccessExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
    }
    
    /**
     * <pre>
     * 메소드명: generateRefreshToken
     * 설명: 로그인 아이디로 리프레시 토큰 생성
     * </pre>
     * @param username
     * @return
     */
    public String generateRefreshToken(String username) {
    	return Jwts.builder()
    			.setSubject(username)
    			.setIssuedAt(new Date())
    			.setExpiration(new Date(new Date().getTime() + jwtRefreshExpirationMs))
    			.signWith(SignatureAlgorithm.HS256, jwtSecretKey)
    			.compact();
    }

    /**
     * <pre>
     * 메소드명: extractClaims
     * 설명: 토큰으로 클레임 추출
     * </pre>
     * @param token
     * @return
     */
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * <pre>
     * 메소드명: extractUsername
     * 설명: 토큰에서 로그인 아이디 추출
     * </pre>
     * @param token
     * @return
     */
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    /**
     * <pre>
     * 메소드명: isTokenExpired
     * 설명: 토큰만료여부
     * </pre>
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    /**
     * <pre>
     * 메소드명: validateToken
     * 설명: 토큰유효여부
     * </pre>
     * @param token
     * @param username
     * @return
     */
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
