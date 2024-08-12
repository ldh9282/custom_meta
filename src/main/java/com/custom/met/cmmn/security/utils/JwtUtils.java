package com.custom.met.cmmn.security.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	
	@Value("${jwt-secret-key}")
	private String jwtSecretKey;
	
	@Value("${jwt-access-expiration-ms}")
	private long jwtAccessExpirationMs;
	
	@Value("${jwt-refresh-expiration-ms}")
	private long jwtRefreshExpirationMs;
	
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtAccessExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
    }
    
    public String generateRefreshToken(String username) {
    	return Jwts.builder()
    			.setSubject(username)
    			.setIssuedAt(new Date())
    			.setExpiration(new Date(new Date().getTime() + jwtRefreshExpirationMs))
    			.signWith(SignatureAlgorithm.HS256, jwtSecretKey)
    			.compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
