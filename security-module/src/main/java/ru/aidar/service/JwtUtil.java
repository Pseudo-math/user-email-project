package ru.aidar.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {
    public static final String SECRET = "44f696cae9b03360519b2bfa7f98930409714e81e2bd702104c01d39175d0ed1";

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().claims(claims).subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey()).compact();
    }

    public String generateToken(String username) {
        return createToken(new HashMap<>(), username);
    }

    public Jws<Claims> validateToken(String token) {
        return Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
    }
}
