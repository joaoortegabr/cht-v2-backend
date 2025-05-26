package com.marpe.cht.jwt;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.marpe.cht.entities.User;
import com.marpe.cht.entities.enums.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private SecretKey secretKey;
    private long expiration;
    
    public JwtService(@Qualifier("jwtProperties") JwtProperties properties) {
        this.secretKey = Keys.hmacShaKeyFor(properties.getSecret().getBytes());
        this.expiration = properties.getExpiration();
    }
   
    public String generateToken(String username, Role role) {
    	Instant now = Instant.now();
    	
    	return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(expiration)))
                .claim("role", role.name())
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String extractUsername(String token) {
    	return getClaims(token).getSubject();
    }
    
    public Role extractRole(String token) {
        String role = getClaims(token).get("role", String.class);
        return Role.valueOf(role);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, User user) {
        String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
    
    private boolean isTokenExpired(String token) {
        Date expiration = ((JwtParserBuilder) Jwts.builder())
                .setSigningKey(secretKey.getEncoded())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
    
}
